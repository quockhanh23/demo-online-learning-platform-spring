package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Course;

public interface CourseService {

    Course createCourse(Course request, Long idUser);

    Course getDetailCourse(Long idCourse);

    Course updateCourse(Course request, Long idUser);
}
