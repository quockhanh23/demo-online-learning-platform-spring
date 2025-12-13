package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.LessonLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {
}
