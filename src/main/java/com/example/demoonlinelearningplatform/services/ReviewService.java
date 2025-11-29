package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.entities.Review;

public interface ReviewService {

    Review createReview(Review request);

    Review getDetailReview(Long idReview);

    Review updateReview(Review request);

    void softDeleteReview(Long idReview);
}
