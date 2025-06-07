package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Course;
import com.example.demoonlinelearningplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/getAllCourse")
    public ResponseEntity<Object> getAllCourse(@RequestParam(defaultValue = "0", required = false) int page,
                                               @RequestParam(defaultValue = "10", required = false) int size,
                                               @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createCourse")
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.createCourse(course, idUser), HttpStatus.CREATED);
    }

    @GetMapping("/getDetailCourse")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idCourse) {
        return new ResponseEntity<>(courseService.getDetailCourse(idCourse), HttpStatus.OK);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course,
                                               @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.updateCourse(course, idUser), HttpStatus.OK);
    }
}