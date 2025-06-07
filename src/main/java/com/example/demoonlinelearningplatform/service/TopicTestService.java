package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.TopicTest;

public interface TopicTestService {

    TopicTest createTopicTest(TopicTest request);

    TopicTestDTO getDetailTopicTest(Long idTopicTest);

    TopicTest updateTopicTest(TopicTest request);
}
