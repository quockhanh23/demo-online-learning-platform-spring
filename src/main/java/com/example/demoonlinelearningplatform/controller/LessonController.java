package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Lesson;
import com.example.demoonlinelearningplatform.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/getAllLessonByCourse")
    public ResponseEntity<Object> getAllLessonByCourse(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "10", required = false) int size,
                                                       @RequestParam Long idCourse,
                                                       @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.getAllLessonByCourse(pageable, idCourse, searchText);
        return new ResponseEntity<>(lessonPage, HttpStatus.OK);
    }

    @GetMapping("/getAllLessonByCourseList")
    public ResponseEntity<Object> getAllLessonByCourseList(@RequestParam Long idCourse,
                                                           @RequestParam(required = false) String searchText) {
        List<Lesson> lessonPage = lessonService.getAllLessonByCourseList(idCourse, searchText);
        return new ResponseEntity<>(lessonPage, HttpStatus.OK);
    }

    @PostMapping("/createLesson")
    public ResponseEntity<Object> createLesson(@RequestBody Lesson lesson, @RequestParam Long idUser) {
        return new ResponseEntity<>(lessonService.createLesson(lesson, idUser), HttpStatus.CREATED);
    }

    @GetMapping("/getDetailLesson")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idLesson) {
        return new ResponseEntity<>(lessonService.getDetailLesson(idLesson), HttpStatus.OK);
    }

    @PutMapping("/updateLesson")
    public ResponseEntity<Object> updateLesson(@RequestBody Lesson lesson, @RequestParam Long idUser) {
        return new ResponseEntity<>(lessonService.updateLesson(lesson, idUser), HttpStatus.OK);
    }

    @GetMapping("/highestPointLesson")
    public ResponseEntity<Object> highestPointLesson(@RequestParam Long idLesson, @RequestParam Long idUser) {
        return new ResponseEntity<>(lessonService.highestPointLesson(idLesson, idUser), HttpStatus.OK);
    }
}
