package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.LessonLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {
}
