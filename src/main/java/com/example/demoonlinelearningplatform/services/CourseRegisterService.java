package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.entities.Course;
import com.example.demoonlinelearningplatform.entities.CourseRegister;

import java.util.List;

public interface CourseRegisterService {

    void registerCourse(Long idCourse, Long idUserRegister);

    List<Course> getAllRegisterCourse(Long idUserRegister);

    List<CourseRegister> getAllRegisterCourseByIdUser(Long idUser);
}
