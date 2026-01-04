package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select * from course", nativeQuery = true)
    Page<Course> getAllCoursePage(Pageable pageable);

    @Query(value = "select * from course WHERE course_name like CONCAT('%', :searchText, '%') ", nativeQuery = true)
    Page<Course> getAllCoursePage(Pageable pageable, @Param("searchText") String searchText);

    @Query(value = "select * from course where id_department = :idDepartment", nativeQuery = true)
    Page<Course> getAllCourseByDepartment(Pageable pageable, Long idDepartment);

    @Query(value = "select * from course WHERE (course_name like CONCAT('%', :searchText, '%')) and id_department = :idDepartment", nativeQuery = true)
    Page<Course> getAllCourseByDepartment(Pageable pageable, @Param("searchText") String searchText, Long idDepartment);

    List<Course> findAllByIdIn(List<Long> idCourse);
}
