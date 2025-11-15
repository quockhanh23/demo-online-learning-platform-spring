package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course createCourse(Course request, Long idUser);

    Course getDetailCourse(Long idCourse);

    Course updateCourse(Course request, Long idUser);

    Page<Course> getAllCoursePage(Pageable pageable, String searchText);
}
