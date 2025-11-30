package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.entities.Review;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.ReviewRepository;
import com.example.demoonlinelearningplatform.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review request) {
        validateReview(request);
        return reviewRepository.save(request);
    }

    void validateReview(Review request) {
        if (request.getContent().length() > 500) throw new InvalidException("Nội dung đánh giá không quá 500 kí tự");
    }

    @Override
    public Review getDetailReview(Long idReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(idReview);
        if (reviewOptional.isEmpty()) throw new InvalidException("Không tìm thấy");
        return reviewOptional.get();
    }

    @Override
    public Review updateReview(Review request) {
        Optional<Review> reviewOptional = reviewRepository.findById(request.getId());
        if (reviewOptional.isEmpty()) throw new InvalidException("Không tìm thấy");
        validateReview(request);
        reviewOptional.get().setContent(request.getContent());
        return reviewRepository.save(reviewOptional.get());
    }

    @Override
    public void softDeleteReview(Long idReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(idReview);
        if (reviewOptional.isEmpty()) throw new InvalidException("Không tìm thấy");
        reviewOptional.get().setStatus("");
        reviewRepository.save(reviewOptional.get());
    }
}
