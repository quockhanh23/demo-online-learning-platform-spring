package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Query(value = """
            select * FROM user 
            WHERE (id != :idUserLogin) 
            and (username like CONCAT('%', :searchText, '%') or full_name like CONCAT('%', :searchText, '%'))
            """, nativeQuery = true)
    Page<User> getAllUserPage(Pageable pageable, @Param("searchText") String searchText, @Param("idUserLogin") Long idUserLogin);

    @Query(
            value = """
                    select u.*
                    from `user` u
                    left join user_role ur on u.id = ur.user_id
                    left join role r on ur.role_id = r.id
                    where r.role_name = :role
                    """,
            countQuery = """
                    select count(u.id)
                    from `user` u
                    left join user_role ur on u.id = ur.user_id
                    left join role r on ur.role_id = r.id
                    where r.role_name = :role
                    """,
            nativeQuery = true
    )
    Page<User> getAllUserPageByRole(Pageable pageable, @Param("role") String role);

    @Query(
            value = """
                    select u.*
                    from `user` u
                    left join user_role ur on u.id = ur.user_id
                    left join role r on ur.role_id = r.id
                    where r.role_name = 'TEACHER' and u.id_department = :idDepartment
                    """,
            countQuery = """
                    select count(u.id)
                    from `user` u
                    left join user_role ur on u.id = ur.user_id
                    left join role r on ur.role_id = r.id
                    where r.role_name = 'TEACHER'and u.id_department = :idDepartment
                    """,
            nativeQuery = true
    )
    Page<User> getAllUserByDepartment(Pageable pageable, @Param("idDepartment") Long idDepartment);
}
