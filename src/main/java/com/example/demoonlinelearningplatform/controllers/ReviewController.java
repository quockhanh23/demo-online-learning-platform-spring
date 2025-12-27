package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.commons.Common;
import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.entities.Course;
import com.example.demoonlinelearningplatform.entities.Review;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.repositories.CourseRepository;
import com.example.demoonlinelearningplatform.repositories.ReviewRepository;
import com.example.demoonlinelearningplatform.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final CourseRegisterRepository courseRegisterRepository;

    /**
     * API này dùng để tạo mới đánh giá
     *
     * @param review: dữ liệu
     * @return void
     */
    @PostMapping("/createReview")
    public ResponseEntity<Object> createReview(@Valid @RequestBody Review review, BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
        List<Review> reviewList = reviewRepository.findAllByIdCourse(review.getIdCourse());
        Course course = courseService.getDetailCourse(review.getIdCourse());
        if (course.getStatus().equals(CommonConstant.INACTIVE)) {
            throw new InvalidException("Khóa học đã ngừng hoạt động không thể đánh giá");
        }
        if (courseRegisterRepository.findByIdCourseAndIdUserRegister(review.getIdCourse(), review.getIdUserAction()) == null) {
            throw new InvalidException("Bạn chưa đăng kí khóa học này");
        }
        if (CollectionUtils.isEmpty(reviewList)) {
            course.setRate(null);
            courseRepository.save(course);
        } else {
            double average = reviewList.stream()
                    .mapToInt(Review::getStarLevel)
                    .average()
                    .orElse(0);
            course.setRate(String.valueOf(average));
            courseRepository.save(course);
        }
        review.setCreatedDate(new Date());
        review.setStatus(CommonConstant.ACTIVE);
        review.setType(CommonConstant.TYPE_REVIEW_COURSE);
        reviewRepository.saveAndFlush(review);

        List<Review> reviewListAfterUpdate = reviewRepository.findAllByIdCourse(review.getIdCourse());
        if (!CollectionUtils.isEmpty(reviewListAfterUpdate)) {
            double average = reviewListAfterUpdate.stream()
                    .mapToInt(Review::getStarLevel)
                    .average()
                    .orElse(0);
            double truncated = Math.floor(average * 100) / 100.0;
            course.setRate(String.valueOf(truncated));
            courseRepository.save(course);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * API này dùng lấy ra thông tin của 1 đánh giá theo id
     *
     * @param idCourse:     id của khóa học
     * @param idUserAction: id của người đang đăng nhập
     * @return đối tượng Review
     */
    @GetMapping("/getDetailReview")
    public ResponseEntity<Object> getDetailReview(@RequestParam Long idCourse, Long idUserAction) {
        Optional<Review> reviewOptional = reviewRepository.findByIdCourseAndIdUserAction(idCourse, idUserAction);
        if (reviewOptional.isPresent()) {
            return new ResponseEntity<>(reviewOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    /**
     * API này dùng để lấy ra toàn bộ danh sách đánh giá của 1 khóa học
     *
     * @param idCourse: id của khóa học
     * @param page:     trang hiện tại
     * @param size:     số phần tử trong 1 Trang
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
