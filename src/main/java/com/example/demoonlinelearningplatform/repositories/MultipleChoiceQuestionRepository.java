package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {

    List<MultipleChoiceQuestion> getAllByIdTopicTest(Long idTopicTest);
}
