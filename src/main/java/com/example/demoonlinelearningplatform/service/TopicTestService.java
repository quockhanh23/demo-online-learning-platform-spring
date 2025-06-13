package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.TopicTest;

import java.util.List;

public interface TopicTestService {

    TopicTest createTopicTest(TopicTest request);

    List<TopicTestDTO> getDetailTopicTestByCourse(Long idTopicTest);

    TopicTestDTO getDetailTopicTestByLesson(Long idTopicTest);

    TopicTest updateTopicTest(TopicTest request);
}
