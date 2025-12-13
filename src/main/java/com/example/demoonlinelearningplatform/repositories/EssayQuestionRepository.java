package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.EssayQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayQuestionRepository extends JpaRepository<EssayQuestion, Long> {

    List<EssayQuestion> getAllByIdTopicTest(Long idTopicTest);
}
