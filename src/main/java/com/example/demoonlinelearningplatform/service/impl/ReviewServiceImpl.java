package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.entity.Review;
import com.example.demoonlinelearningplatform.repository.ReviewRepository;
import com.example.demoonlinelearningplatform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review request) {
        return reviewRepository.save(request);
    }

    @Override
    public Review getDetailReview(Long idReview) {
        return reviewRepository.findById(idReview).get();
    }

    @Override
    public Review updateReview(Review request) {
        Optional<Review> reviewOptional = reviewRepository.findById(request.getId());
        reviewOptional.get().setContent(request.getContent());
        reviewRepository.save(reviewOptional.get());
        return null;
    }

    @Override
    public void softDeleteReview(Long idReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(idReview);
        reviewOptional.get().setStatus("");
        reviewRepository.save(reviewOptional.get());
    }
}
