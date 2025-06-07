package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EssayQuestionRepository extends JpaRepository<EssayQuestion, Long> {

    List<EssayQuestion> getAllByIdTopicTest(Long idTopicTest);
}
