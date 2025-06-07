package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
