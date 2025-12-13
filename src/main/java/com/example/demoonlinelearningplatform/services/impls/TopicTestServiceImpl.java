package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.dtos.TopicTestDTO;
import com.example.demoonlinelearningplatform.entities.EssayQuestion;
import com.example.demoonlinelearningplatform.entities.Lesson;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entities.TopicTest;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.EssayQuestionRepository;
import com.example.demoonlinelearningplatform.repositories.LessonRepository;
import com.example.demoonlinelearningplatform.repositories.MultipleChoiceQuestionRepository;
import com.example.demoonlinelearningplatform.repositories.TopicTestRepository;
import com.example.demoonlinelearningplatform.services.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TopicTestServiceImpl implements TopicTestService {

    private final TopicTestRepository topicTestRepository;
    private final LessonRepository lessonRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final EssayQuestionRepository essayQuestionRepository;

    @Override
    public TopicTest createTopicTest(TopicTest request) {
        Optional<TopicTest> topicTestOptional = topicTestRepository.getTopByIdLessonOrderByCreateDateDesc(request.getIdLesson());
        Optional<Lesson> lessonOptional = lessonRepository.findById(request.getIdLesson());
        Long idCourse = lessonOptional.isEmpty() ? 0 : lessonOptional.get().getIdCourse();
        request.setIdCourse(idCourse);
        if (topicTestOptional.isPresent()) {
            if (CommonConstant.COMPLETE.equals(topicTestOptional.get().getStatus())) {
                request.setCreateDate(new Date());
                return topicTestRepository.save(request);
            } else {
                return topicTestOptional.get();
            }
        } else {
            return topicTestRepository.save(request);
        }
    }

    @Override
    public List<TopicTestDTO> getDetailTopicTestByCourse(Long idCourse) {
        List<TopicTest> topicTestList = topicTestRepository.getAllByIdCourse(idCourse);
        if (CollectionUtils.isEmpty(topicTestList)) return List.of();
        List<TopicTestDTO> topicTestDTOS = new ArrayList<>();
        for (int i = 0; i < topicTestList.size(); i++) {
            TopicTestDTO topicTestDTO = getData(topicTestList.get(i));
            topicTestDTOS.add(topicTestDTO);
        }
        return topicTestDTOS;
    }

    @Override
    public TopicTestDTO getDetailTopicTestByLesson(Long idLesson) {
        Optional<TopicTest> topicTestOptional = topicTestRepository.getTopByIdLessonOrderByCreateDateDesc(idLesson);
        if (topicTestOptional.isEmpty()) throw new InvalidException("không tìm thấy đề");
        return getData(topicTestOptional.get());
    }

    @Override
    public TopicTestDTO findById(Long idTopicTest) {
        Optional<TopicTest> topicTestOptional = topicTestRepository.findById(idTopicTest);
        if (topicTestOptional.isEmpty()) throw new InvalidException("không tìm thấy đề");
        return getData(topicTestOptional.get());
    }

    private TopicTestDTO getData(TopicTest topicTestOptional) {
        List<EssayQuestion> essayQuestionList = essayQuestionRepository
                .getAllByIdTopicTest(topicTestOptional.getId());

        List<MultipleChoiceQuestion> multipleChoiceQuestionList = multipleChoiceQuestionRepository
                .getAllByIdTopicTest(topicTestOptional.getId());
        TopicTestDTO topicTestDTO = new TopicTestDTO();
        BeanUtils.copyProperties(topicTestOptional, topicTestDTO);
        topicTestDTO.setEssayQuestionList(essayQuestionList);
        topicTestDTO.setMultipleChoiceQuestionList(multipleChoiceQuestionList);
        return topicTestDTO;
    }

    @Override
    public TopicTest updateTopicTest(TopicTest request) {
        return topicTestRepository.save(request);
    }

    @Override
    public TopicTest updateStatusTopicTest(String status, Long idTopicTest) {
        Optional<TopicTest> topicTestOptional = topicTestRepository.findById(idTopicTest);
        if (topicTestOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy");
        }
        topicTestOptional.get().setStatus(CommonConstant.COMPLETE);
        return topicTestRepository.save(topicTestOptional.get());
    }
}
