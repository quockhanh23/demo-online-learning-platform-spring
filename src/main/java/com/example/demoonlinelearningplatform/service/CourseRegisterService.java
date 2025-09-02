package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.CourseInformation;
import com.example.demoonlinelearningplatform.entity.Course;

import java.util.List;

public interface CourseRegisterService {

    void registerCourse(Long idCourse, Long idUserRegister);

    List<Course> getAllRegisterCourse(Long idUserRegister);

    CourseInformation getAllInformation(Long idUser, Long idLesson);
}
