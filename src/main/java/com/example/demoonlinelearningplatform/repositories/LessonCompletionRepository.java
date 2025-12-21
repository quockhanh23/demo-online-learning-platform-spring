package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.LessonCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long> {

    List<LessonCompletion> findAllByIdCourseIn(List<Long> ids);

    Optional<LessonCompletion> findByIdLesson(Long idLesson);
}
