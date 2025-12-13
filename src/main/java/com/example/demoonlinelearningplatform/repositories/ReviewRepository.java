package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select * FROM review WHERE idCourse = :idUserLogin", nativeQuery = true)
    Page<Review> getAllReviewPage(Pageable pageable, Long idCourse);

    List<Review> findAllByIdCourse(Long idCourse);

    Optional<Review> findByIdCourseAndIdUserAction(Long idCourse, Long idUser);

}
