package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.entity.Test;

import java.util.List;

public interface TestService {

    Test createTest(Test request);

    Test updateTime(Long idTest, int time);

    TestDTO getDetailTest(Long idTest);

    List<TestDTO> getAllTestByUserAndLesson(Long idUser, Long idLesson);
}
