package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Long> {

    List<MultipleChoiceAnswer> getAllByIdTest(Long idTest);

    List<MultipleChoiceAnswer> getAllByIdTestIn(List<Long> idTest);
}
