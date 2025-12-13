package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course createCourse(Course request, Long idUser);

    Course getDetailCourse(Long idCourse);

    Course updateCourse(Course request, Long idUser);

    Page<Course> getAllCoursePage(Pageable pageable, String searchText);
}
