package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.EssayAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EssayAnswerRepository extends JpaRepository<EssayAnswer, Long> {
    List<EssayAnswer> getAllByIdTest(Long idTest);

    List<EssayAnswer> getAllByIdTestIn(List<Long> idTest);

}
