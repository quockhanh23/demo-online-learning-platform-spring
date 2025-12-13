package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = """
           SELECT *
           FROM department
           WHERE
               CASE
                   WHEN :searchText IS NULL OR :searchText = '' THEN 1
                   ELSE name LIKE CONCAT('%', :searchText, '%')
               END
           AND
               CASE
                   WHEN :status IS NULL OR :status = '' THEN 1
                   ELSE status = :status
               END;
                                                           
            """, nativeQuery = true)
    Page<Department> getAllDepartmentPage(Pageable pageable,
                                          @Param("searchText") String searchText,
                                          @Param("status") String status);
}
