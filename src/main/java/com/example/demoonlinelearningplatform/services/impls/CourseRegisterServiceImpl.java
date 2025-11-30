package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.entities.Course;
import com.example.demoonlinelearningplatform.entities.CourseRegister;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.repositories.CourseRepository;
import com.example.demoonlinelearningplatform.repositories.LessonRepository;
import com.example.demoonlinelearningplatform.repositories.TestRepository;
import com.example.demoonlinelearningplatform.services.CourseRegisterService;
import com.example.demoonlinelearningplatform.services.TestService;
import com.example.demoonlinelearningplatform.services.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        courseRegister.setStatus(CommonConstant.STUDYING);
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
    public List<CourseRegister> getAllRegisterCourseByIdUser(Long idUser) {
        List<CourseRegister> courseRegisters = registerRepository.findAllByIdUserRegister(idUser);
        if (CollectionUtils.isEmpty(courseRegisters)) return List.of();
        return courseRegisters;
    }
}
