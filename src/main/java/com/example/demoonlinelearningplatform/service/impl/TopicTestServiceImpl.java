package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entity.TopicTest;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.EssayQuestionRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceQuestionRepository;
import com.example.demoonlinelearningplatform.repository.TopicTestRepository;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicTestServiceImpl implements TopicTestService {

    private final TopicTestRepository topicTestRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final EssayQuestionRepository essayQuestionRepository;

    @Override
    public TopicTest createTopicTest(TopicTest request) {
        return topicTestRepository.save(request);
    }

    @Override
    public TopicTestDTO getDetailTopicTest(Long idTopicTest) {
        Optional<TopicTest> topicTestOptional = topicTestRepository.findById(idTopicTest);
        if (topicTestOptional.isEmpty()) throw new InvalidException("không tìm thấy đề");

        List<EssayQuestion> essayQuestionList = essayQuestionRepository
                .getAllByIdTopicTest(topicTestOptional.get().getId());

        List<MultipleChoiceQuestion> multipleChoiceQuestionList = multipleChoiceQuestionRepository
                .getAllByIdTopicTest(topicTestOptional.get().getId());
        TopicTestDTO topicTestDTO = new TopicTestDTO();
        BeanUtils.copyProperties(topicTestOptional.get(), topicTestDTO);
        topicTestDTO.setEssayQuestionList(essayQuestionList);
        topicTestDTO.setMultipleChoiceQuestionList(multipleChoiceQuestionList);
        return topicTestDTO;
    }

    @Override
    public TopicTest updateTopicTest(TopicTest request) {
        return topicTestRepository.save(request);
    }
}
