package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.common.CommonConstant;
import com.example.demoonlinelearningplatform.entity.CourseRegister;
import com.example.demoonlinelearningplatform.repository.CourseRegisterRepository;
import com.example.demoonlinelearningplatform.service.CourseRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseRegisterServiceImpl implements CourseRegisterService {

    private final CourseRegisterRepository registerRepository;

    @Override
    public void registerCourse(Long idCourse, Long idUserRegister) {
        CourseRegister courseRegister = new CourseRegister();
        courseRegister.setIdCourse(idCourse);
        courseRegister.setIdUserRegister(idUserRegister);
        courseRegister.setStatus(CommonConstant.ACTIVE);
        registerRepository.save(courseRegister);
    }
}
