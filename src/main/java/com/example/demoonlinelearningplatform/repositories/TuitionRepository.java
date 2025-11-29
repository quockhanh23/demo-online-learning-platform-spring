package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TuitionRepository extends JpaRepository<Tuition, Long> {

    List<Tuition> findAllByIdUser(Long idUser);
}
