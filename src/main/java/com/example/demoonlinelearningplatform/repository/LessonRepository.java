package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(value = "select * from lesson where id_course = :idCourse", nativeQuery = true)
    Page<Lesson> getAllLessonByCoursePage(Pageable pageable, Long idCourse);

    @Query(value = "select * from lesson WHERE id_course = :idCourse and lesson_name like CONCAT('%', :searchText, '%') ", nativeQuery = true)
    Page<Lesson> getAllLessonByCoursePage(Pageable pageable, @Param("searchText") String searchText, @Param("idCourse") Long idCourse);

    int countAllByIdCourse(Long idCourse);

    @Query(value = "select * from lesson WHERE id_course = :idCourse and lesson_name like CONCAT('%', :searchText, '%') ", nativeQuery = true)
    List<Lesson> findByIdCourse(Long idCourse, @Param("searchText") String searchText);

    List<Lesson> findByIdCourse(Long idCourse);
}
