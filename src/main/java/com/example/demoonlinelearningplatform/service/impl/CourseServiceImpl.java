package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.common.CommonConstant;
import com.example.demoonlinelearningplatform.entity.Course;
import com.example.demoonlinelearningplatform.entity.CourseLog;
import com.example.demoonlinelearningplatform.repository.CourseLogRepository;
import com.example.demoonlinelearningplatform.repository.CourseRepository;
import com.example.demoonlinelearningplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseLogRepository courseLogRepository;

    @Override
    @Transactional
    public Course createCourse(Course request, Long idUser) {
        request.setStatus(CommonConstant.ACTIVE);
        Course course = courseRepository.save(request);
        createCourseLog(course, idUser);
        return course;
    }

    @Override
    public Course getDetailCourse(Long idCourse) {
        Optional<Course> courseOptional = courseRepository.findById(idCourse);
        return courseOptional.get();
    }

    @Override
    @Transactional
    public Course updateCourse(Course request, Long idUser) {
        Optional<Course> courseOptional = courseRepository.findById(request.getId());
        if (courseOptional.isPresent()) {
            courseOptional.get().setCourseName(request.getCourseName());
            courseOptional.get().setCourseDescription(request.getCourseDescription());
            courseOptional.get().setStartDate(request.getStartDate());
            courseOptional.get().setEndDate(request.getEndDate());
            courseOptional.get().setUpdatedDate(new Date());
            createCourseLog(request, idUser);
            return courseOptional.get();
        }
        return null;
    }

    private void createCourseLog(Course request, Long idUser) {
        CourseLog courseLog = CourseLog.builder()
                .courseName(request.getCourseName())
                .courseDescription(request.getCourseDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .createdDate(new Date())
                .updatedDate(new Date())
                .idUserAction(idUser)
                .idCourse(request.getId())
                .build();
        courseLogRepository.save(courseLog);
    }
}
