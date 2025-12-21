package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.services.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summaries")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    /**
     * Bảng thông tin tổng quan về các khóa học đã đăng kí
     * Bao gồm trạng thái khóa học (đã học xong hay chưa xong), điểm các bài học trong khóa học...
     *
     * @param idUserLogin: id của người dùng đang đăng nhập
     * @param page:        trang hiện tại
     * @param size:        số phần tử trong 1 Trang
     * @param searchText:  search value
     * @return danh sách thông tin tổng quan
     */
    @GetMapping("/courseSummary")
    public ResponseEntity<Object> courseSummary(@RequestParam Long idUserLogin,
                                                @RequestParam(defaultValue = "0", required = false) int page,
                                                @RequestParam(defaultValue = "10", required = false) int size,
                                                @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(summaryService.coursesSummary(idUserLogin), HttpStatus.OK);
    }

    @GetMapping("/checkCompleteLesson")
    public ResponseEntity<Object> checkCompleteLesson(@RequestParam Long idUserLogin, @RequestParam Long idLesson) {
        return new ResponseEntity<>(summaryService.checkCompleteLesson(idUserLogin, idLesson), HttpStatus.OK);
    }

    @GetMapping("/checkCompleteCourse")
    public ResponseEntity<Object> checkCompleteCourse(@RequestParam Long idUserLogin) {
        return new ResponseEntity<>(summaryService.checkCompleteCourse(idUserLogin), HttpStatus.OK);
    }
}
