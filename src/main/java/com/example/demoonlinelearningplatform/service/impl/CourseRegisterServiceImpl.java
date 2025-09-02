package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.common.CommonConstant;
import com.example.demoonlinelearningplatform.dto.*;
import com.example.demoonlinelearningplatform.entity.*;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.repository.CourseRepository;
import com.example.demoonlinelearningplatform.repository.LessonRepository;
import com.example.demoonlinelearningplatform.repository.TestRepository;
import com.example.demoonlinelearningplatform.service.CourseRegisterService;
import com.example.demoonlinelearningplatform.service.TestService;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseRegisterServiceImpl implements CourseRegisterService {

    private final CourseRegisterRepository registerRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final TestRepository testRepository;
    private final TopicTestService topicTestService;
    private final TestService testService;

    @Override
    public void registerCourse(Long idCourse, Long idUserRegister) {
        boolean isAllowRegister = registerRepository.existsAllByIdCourseAndIdUserRegister(idCourse, idUserRegister);
        if (isAllowRegister) {
            throw new InvalidException("Bạn đã đăng kí khóa học này rồi");
        }
        CourseRegister courseRegister = new CourseRegister();
        courseRegister.setIdCourse(idCourse);
        courseRegister.setIdUserRegister(idUserRegister);
        courseRegister.setStatus(CommonConstant.ACTIVE);
        registerRepository.save(courseRegister);
    }

    @Override
    public List<Course> getAllRegisterCourse(Long idUserRegister) {
        List<CourseRegister> courseRegisters = registerRepository.findAllByIdUserRegister(idUserRegister);
        if (CollectionUtils.isEmpty(courseRegisters)) return List.of();
        return courseRepository
                .findAllByIdIn(courseRegisters.stream().map(CourseRegister::getIdCourse).toList());
    }

    @Override
    public CourseInformation getAllInformation(Long idUser, Long idLesson) {

        return null;
    }
}
