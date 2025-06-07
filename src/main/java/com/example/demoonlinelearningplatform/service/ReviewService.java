package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Review;

public interface ReviewService {

    Review createReview(Review request);

    Review getDetailReview(Long idReview);

    Review updateReview(Review request);

    void softDeleteReview(Long idReview);
}
