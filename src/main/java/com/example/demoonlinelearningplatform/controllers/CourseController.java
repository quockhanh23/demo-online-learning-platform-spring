package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.entities.Course;
import com.example.demoonlinelearningplatform.repositories.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.services.CourseRegisterService;
import com.example.demoonlinelearningplatform.services.CourseService;
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

    /**
     * API này dùng để lấy danh sách khóa học
     *
     * @param page:       trang hiện tại
     * @param size:       số phần tử trong 1 Trang
     * @param searchText: search value
     * @return danh sách khóa học (phân trang)
     */
    @GetMapping("/getAllCourse")
    public ResponseEntity<Object> getAllCourse(@RequestParam(defaultValue = "0", required = false) int page,
                                               @RequestParam(defaultValue = "8", required = false) int size,
                                               @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.getAllCoursePage(pageable, searchText);
        return new ResponseEntity<>(coursePage, HttpStatus.OK);
    }

    @GetMapping("/getAllCourseByDepartment")
    public ResponseEntity<Object> getAllCourseByDepartment(@RequestParam(defaultValue = "0", required = false) int page,
                                                           @RequestParam(defaultValue = "8", required = false) int size,
                                                           @RequestParam(required = false) String searchText,
                                                           @RequestParam Long idDepartment) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.getAllCourseByDepartment(pageable, searchText, idDepartment);
        return new ResponseEntity<>(coursePage, HttpStatus.OK);
    }

    /**
     * API này lấy ra danh sách khóa học mà người dùng đã đăng kí
     *
     * @param idUserRegister: id của người dùng
     * @return danh sách khóa học
     */
    @GetMapping("/getAllRegisterCourse")
    public ResponseEntity<Object> getAllRegisterCourse(@RequestParam Long idUserRegister) {
        return new ResponseEntity<>(courseRegisterService.getAllRegisterCourse(idUserRegister), HttpStatus.OK);
    }

    /**
     * API này để người dùng đăng kí khóa học
     *
     * @param idCourse:       id của khóa học
     * @param idUserRegister: id của người dùng
     * @return void
     */
    @PostMapping("/registerCourse")
    public ResponseEntity<Object> registerCourse(@RequestParam Long idCourse, @RequestParam Long idUserRegister) {
        courseRegisterService.registerCourse(idCourse, idUserRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * API này để tạo ra khóa học
     *
     * @param course: thông tin khóa học
     * @param idUser: id của người tạo khóa học
     * @return thông tin khóa học
     */
    @PostMapping("/createCourse")
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.createCourse(course, idUser), HttpStatus.CREATED);
    }

    /**
     * API này để tạo lấy thông tin của 1 khóa học
     *
     * @param idCourse: id của khóa học
     * @return thông tin khóa học
     */
    @GetMapping("/getDetailCourse")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idCourse) {
        return new ResponseEntity<>(courseService.getDetailCourse(idCourse), HttpStatus.OK);
    }

    /**
     * Kiểm tra xem khóa học đã được người dùng đăng kí hay chưa
     *
     * @param idCourse:      id của khóa học để kiểm tra
     * @param idUserRegister id của người dùng để kiểm tra
     * @return giá trị
     */
    @GetMapping("/checkRegister")
    public ResponseEntity<Object> checkRegister(@RequestParam Long idCourse, @RequestParam Long idUserRegister) {
        boolean check = courseRegisterRepository.existsAllByIdCourseAndIdUserRegister(idCourse, idUserRegister);
        return new ResponseEntity<>(Map.of("value", check ? "1" : "2"), HttpStatus.OK);
    }

    /**
     * API này để cập nhật lại thông tin của 1 khóa học
     *
     * @param course: thông tin mới được cập nhật
     * @param idUser: id của người cập nhật khóa học
     * @return thông tin của khóa học
     */
    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course,
                                               @RequestParam Long idUser) {
        return new ResponseEntity<>(courseService.updateCourse(course, idUser), HttpStatus.OK);
    }
}