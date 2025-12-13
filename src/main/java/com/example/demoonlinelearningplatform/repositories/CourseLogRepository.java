package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.CourseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLogRepository extends JpaRepository<CourseLog, Long> {
}
