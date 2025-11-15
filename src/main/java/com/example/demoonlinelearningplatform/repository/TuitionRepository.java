package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TuitionRepository extends JpaRepository<Tuition, Long> {

    List<Tuition> findAllByIdUser(Long idUser);
}
