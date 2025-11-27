package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Review;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    /**
     * API này dùng để tạo mới đánh giá
     *
     * @param review: dữ liệu
     * @return void
     */
    @PostMapping("/createReview")
    public ResponseEntity<Object> createReview(@RequestBody Review review) {
        reviewRepository.save(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * API này dùng lấy ra thông tin của 1 đánh giá theo id
     *
     * @param idReview: id của đối tượng Review
     * @return đối tượng Review
     */
    @GetMapping("/getDetailReview")
    public ResponseEntity<Object> getDetailReview(@RequestParam Long idReview) {
        Optional<Review> reviewOptional = reviewRepository.findById(idReview);
        if (reviewOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy");
        }
        return new ResponseEntity<>(reviewOptional.get(), HttpStatus.OK);
    }

    /**
     * API này dùng để lấy ra toàn bộ danh sách đánh giá của 1 khóa học
     *
     * @param idCourse:   id của khóa học
     * @param page:       trang hiện tại
     * @param size:       số phần tử trong 1 Trang
     * @param searchText: search value
     * @return danh danh sách đánh giá của khóa học (phân trang)
     */

    @GetMapping("/getAllReview")
    public ResponseEntity<Object> getAllReview(
            @RequestParam Long idCourse,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewRepository.getAllReviewPage(pageable, idCourse);
        return new ResponseEntity<>(reviewPage, HttpStatus.OK);
    }

    /**
     * API này dùng để cập nhật đánh giá
     *
     * @param review: dữ liệu
     * @return void
     */
    @PutMapping("/updateReview")
    public ResponseEntity<Object> updateInformation(@RequestBody Review review) {
        Optional<Review> reviewOptional = reviewRepository.findById(review.getId());
        if (reviewOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy");
        }
        reviewOptional.get().setContent(review.getContent());
        reviewOptional.get().setUpdatedDate(new Date());
        reviewRepository.save(reviewOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
