package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.CourseCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCompletionRepository extends JpaRepository<CourseCompletion, Long> {

    List<CourseCompletion> findAllByIdUser(Long idUser);
}
