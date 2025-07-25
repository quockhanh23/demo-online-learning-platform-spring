package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.entity.EssayAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entity.Test;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.EssayAnswerRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceAnswerRepository;
import com.example.demoonlinelearningplatform.repository.TestRepository;
import com.example.demoonlinelearningplatform.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;
    private final EssayAnswerRepository essayAnswerRepository;

    @Override
    public Test createTest(Test request) {
        return testRepository.saveAndFlush(request);
    }

    @Override
    public Test updateTime(Long idTest, int time) {
        Optional<Test> testOptional = testRepository.findById(idTest);
        if (testOptional.isEmpty()) throw new InvalidException("không tìm thấy bài làm");
        testOptional.get().setTime(time);
        return testRepository.saveAndFlush(testOptional.get());
    }

    @Override
    public TestDTO getDetailTest(Long idTest) {
        Optional<Test> testOptional = testRepository.findById(idTest);
        if (testOptional.isEmpty()) throw new InvalidException("không tìm thấy bài làm");
        TestDTO testDTO = new TestDTO();
        BeanUtils.copyProperties(testOptional.get(), testDTO);
        List<MultipleChoiceAnswer> multipleChoiceAnswerList = multipleChoiceAnswerRepository.getAllByIdTest(idTest);
        List<EssayAnswer> essayAnswerList = essayAnswerRepository.getAllByIdTest(idTest);
        testDTO.setEssayAnswerList(essayAnswerList);
        testDTO.setMultipleChoiceAnswerList(multipleChoiceAnswerList);
        return testDTO;
    }

    @Override
    public List<TestDTO> getAllTestByUserAndLesson(Long idUser, Long idLesson) {
        List<TestDTO> dtoList = new ArrayList<>();
        List<Test> testList = testRepository.getAllTestByIdStudentAndIdLesson(idUser, idLesson);
        if (CollectionUtils.isEmpty(testList)) return List.of();
        List<Long> listIdTest = testList.stream().map(Test::getId).toList();
        List<MultipleChoiceAnswer> multipleChoiceAnswerList = multipleChoiceAnswerRepository.getAllByIdTestIn(listIdTest);
        List<EssayAnswer> essayAnswerList = essayAnswerRepository.getAllByIdTestIn(listIdTest);
        for (Test test : testList) {
            TestDTO testDTO = new TestDTO();
            BeanUtils.copyProperties(test, testDTO);
            Long idTest = testDTO.getId();
            testDTO.setEssayAnswerList(essayAnswerList.stream()
                    .filter(item -> item.getIdTest().equals(idTest)).toList());
            testDTO.setMultipleChoiceAnswerList(multipleChoiceAnswerList.stream()
                    .filter(item -> item.getIdTest().equals(idTest)).toList());
            dtoList.add(testDTO);
        }
        return dtoList;
    }
}
