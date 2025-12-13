package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.dtos.TestDTO;
import com.example.demoonlinelearningplatform.entities.Test;

import java.util.List;

public interface TestService {

    Test createTest(Test request);

    Test updateTime(Long idTest, int time);

    TestDTO getDetailTest(Long idTest);

    TestDTO getDetailTestByIdUserAndIdTopicTest(Long idUser, Long idTopicTest);

    List<TestDTO> getAllTestByUserAndLesson(Long idUser, Long idLesson);
}
