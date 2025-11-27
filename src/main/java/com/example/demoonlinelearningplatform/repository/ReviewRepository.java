package com.example.demoonlinelearningplatform.repository;

import com.example.demoonlinelearningplatform.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select * FROM review WHERE idCourse = :idUserLogin", nativeQuery = true)
    Page<Review> getAllReviewPage(Pageable pageable, Long idCourse);

}
