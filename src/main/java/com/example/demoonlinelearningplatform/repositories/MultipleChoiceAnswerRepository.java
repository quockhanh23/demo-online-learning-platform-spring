package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Long> {

    List<MultipleChoiceAnswer> getAllByIdTest(Long idTest);

    List<MultipleChoiceAnswer> getAllByIdTestIn(List<Long> idTest);
}
