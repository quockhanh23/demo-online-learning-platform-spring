package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.commons.Common;
import com.example.demoonlinelearningplatform.entities.Lesson;
import com.example.demoonlinelearningplatform.services.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    /**
     * API này dùng để lấy danh sách các bài học trong khóa học
     *
     * @param page:       trang hiện tại
     * @param size:       số phần tử trong 1 Trang
     * @param idCourse:   id của khóa học
     * @param searchText: search value
     * @return danh sách các bài học trong khóa học (phân trang)
     */
    @GetMapping("/getAllLessonByCourse")
    public ResponseEntity<Object> getAllLessonByCourse(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "10", required = false) int size,
                                                       @RequestParam Long idCourse,
                                                       @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.getAllLessonByCourse(pageable, idCourse, searchText);
        return new ResponseEntity<>(lessonPage, HttpStatus.OK);
    }

    /**
     * API này dùng để lấy danh sách các bài học trong khóa học
     *
     * @param idCourse:   id của khóa học
     * @param searchText: search value
     * @return danh sách các bài học trong khóa học
     */
    @GetMapping("/getAllLessonByCourseList")
    public ResponseEntity<Object> getAllLessonByCourseList(@RequestParam Long idCourse,
                                                           @RequestParam(required = false) String searchText) {
        List<Lesson> lessonPage = lessonService.getAllLessonByCourseList(idCourse, searchText);
        return new ResponseEntity<>(lessonPage, HttpStatus.OK);
    }

    /**
     * API này dùng để tạo mới bài học trong khóa học
     *
     * @param lesson: thông tin bài học
     * @param idUser: id của người dùng tạo bài học
     * @return thông tin bài học
     */
    @PostMapping("/createLesson")
    public ResponseEntity<Object> createLesson(@Valid @RequestBody Lesson lesson,
                                               @RequestParam Long idUser,
                                               BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
        return new ResponseEntity<>(lessonService.createLesson(lesson, idUser), HttpStatus.CREATED);
    }

    /**
     * API này dùng để lấy ra toàn bộ thông tin của 1 bài học
     *
     * @param idLesson: id của bài học
     * @return thông tin của 1 bài học
     */
    @GetMapping("/getDetailLesson")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idLesson) {
        return new ResponseEntity<>(lessonService.getDetailLesson(idLesson), HttpStatus.OK);
    }

    /**
     * API này dùng để cập nhật thông tin của 1 bài học
     *
     * @param lesson: dữ liệu mới của bài học
     * @param idUser: id của người dùng cập nhật bài học
     * @return thông tin của 1 bài học
     */
    @PutMapping("/updateLesson")
    public ResponseEntity<Object> updateLesson(@RequestBody Lesson lesson, @RequestParam Long idUser) {
        return new ResponseEntity<>(lessonService.updateLesson(lesson, idUser), HttpStatus.OK);
    }

    /**
     * Lấy ra điểm cao nhất trong các lần làm bài của bài học đó
     *
     * @param idLesson: id của bài học
     * @param idUser:   id của người dùng
     * @return thông tin bài kiểm tra có điểm cao nhất
     */
    @GetMapping("/highestPointLesson")
    public ResponseEntity<Object> highestPointLesson(@RequestParam Long idLesson, @RequestParam Long idUser) {
        return new ResponseEntity<>(lessonService.highestPointLesson(idLesson, idUser), HttpStatus.OK);
    }
}
