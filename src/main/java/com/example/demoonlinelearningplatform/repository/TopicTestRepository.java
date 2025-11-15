package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.TopicTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicTestRepository extends JpaRepository<TopicTest, Long> {

    List<TopicTest> getAllByIdCourse(Long idCourse);

    Optional<TopicTest> getTopByIdLessonOrderByCreateDateDesc(Long idLesson);
}
