package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.dtos.TopicTestDTO;
import com.example.demoonlinelearningplatform.entities.TopicTest;

import java.util.List;

public interface TopicTestService {

    TopicTest createTopicTest(TopicTest request);

    List<TopicTestDTO> getDetailTopicTestByCourse(Long idTopicTest);

    TopicTestDTO getDetailTopicTestByLesson(Long idLesson);

    TopicTestDTO findById(Long idTopicTest);

    TopicTest updateTopicTest(TopicTest request);

    TopicTest updateStatusTopicTest(String status, Long idTopicTest);
}
