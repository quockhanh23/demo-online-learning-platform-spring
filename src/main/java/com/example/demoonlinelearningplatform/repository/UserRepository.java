package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Query(value = "select * FROM user WHERE (id != :idUserLogin) and (username like CONCAT('%', :searchText, '%') or full_name like CONCAT('%', :searchText, '%'))", nativeQuery = true)
    Page<User> getAllUserPage(Pageable pageable, @Param("searchText") String searchText, @Param("idUserLogin") Long idUserLogin);

    @Query(value = "select user.*\n" +
            "from user\n" +
            "         left join user_role ur on user.id = ur.user_id\n" +
            "         left join role r on ur.role_id = r.id\n" +
            "where (r.role_name = :role)", nativeQuery = true)
    Page<User> getAllUserPageByRole(Pageable pageable, @Param("role") String role);
}
