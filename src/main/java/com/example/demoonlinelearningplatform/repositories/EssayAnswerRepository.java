package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.EssayAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayAnswerRepository extends JpaRepository<EssayAnswer, Long> {
    List<EssayAnswer> getAllByIdTest(Long idTest);

    List<EssayAnswer> getAllByIdTestIn(List<Long> idTest);

}
