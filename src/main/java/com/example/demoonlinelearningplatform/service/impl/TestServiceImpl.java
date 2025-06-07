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
        return testRepository.save(request);
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
    public Test updateTest(Test request) {
        Optional<Test> testOptional = testRepository.findById(request.getId());
        testOptional.get().setContent(request.getContent());
        testOptional.get().setContent(request.getContent());
        return null;
    }
}
