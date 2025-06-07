package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.EssayAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EssayAnswerRepository extends JpaRepository<EssayAnswer, Long> {
    List<EssayAnswer> getAllByIdTest(Long idTest);

}
