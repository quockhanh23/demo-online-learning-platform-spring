package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Course;
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

    List<Course> findAllByIdIn(List<Long> idCourse);
}
