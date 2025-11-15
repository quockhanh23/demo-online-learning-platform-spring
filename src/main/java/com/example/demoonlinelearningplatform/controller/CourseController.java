package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Course;
import com.example.demoonlinelearningplatform.repository.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.service.CourseRegisterService;
import com.example.demoonlinelearningplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseRegisterService courseRegisterService;
    private final CourseRegisterRepository courseRegisterRepository;

    @GetMapping("/getAllCourse")
    public ResponseEntity<Object> getAllCourse(@RequestParam(defaultValue = "0", required = false) int page,
                                               @RequestParam(defaultValue = "8", required = false) int size,
                                               @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.getAllCoursePage(pageable, searchText);
        return new ResponseEntity<>(coursePage, HttpStatus.OK);
    }

    @GetMapping("/getAllRegisterCourse")
    public ResponseEntity<Object> getAllRegisterCourse(@RequestParam Long idUserRegister) {
        return new ResponseEntity<>(courseRegisterService.getAllRegisterCourse(idUserRegister), HttpStatus.OK);
    }

    @GetMapping("/getAllInformation")
    public ResponseEntity<Object> getAllInformation(@RequestParam Long idUser, @RequestParam Long idLesson) {
        return new ResponseEntity<>(courseRegisterService.getAllInformation(idUser, idLesson), HttpStatus.OK);
    }

    @PostMapping("/registerCourse")
    public ResponseEntity<Object> registerCourse(@RequestParam Long idCourse, @RequestParam Long idUserRegister) {
        courseRegisterService.registerCourse(idCourse, idUserRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createCourse")
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.createCourse(course, idUser), HttpStatus.CREATED);
    }

    @GetMapping("/getDetailCourse")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idCourse) {
        return new ResponseEntity<>(courseService.getDetailCourse(idCourse), HttpStatus.OK);
    }

    @GetMapping("/checkRegister")
    public ResponseEntity<Object> checkRegister(@RequestParam Long idCourse, @RequestParam Long idUserRegister) {
        boolean check = courseRegisterRepository.existsAllByIdCourseAndIdUserRegister(idCourse, idUserRegister);
        return new ResponseEntity<>(Map.of("value", check ? "1" : "2"), HttpStatus.OK);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course,
                                               @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.updateCourse(course, idUser), HttpStatus.OK);
    }
}