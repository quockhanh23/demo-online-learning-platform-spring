package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.TopicTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicTestRepository extends JpaRepository<TopicTest, Long> {
}
