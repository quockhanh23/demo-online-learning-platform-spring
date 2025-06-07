package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {

    List<MultipleChoiceQuestion> getAllByIdTopicTest(Long idTopicTest);
}
