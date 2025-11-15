package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> getAllTestByIdStudentAndIdLesson(Long idUser, Long idLesson);

    Optional<Test> findByIdStudentAndIdTopicTest(Long idUser, Long idTopicTest);

    List<Test> findAllByIdCourseIn(List<Long> idCourses);
}
