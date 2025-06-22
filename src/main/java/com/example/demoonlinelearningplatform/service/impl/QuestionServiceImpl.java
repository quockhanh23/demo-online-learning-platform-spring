package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.EssayQuestionRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceQuestionRepository;
import com.example.demoonlinelearningplatform.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final MultipleChoiceQuestionRepository questionRepository;
    private final EssayQuestionRepository essayQuestionRepository;

    @Override
    public void createMultipleChoiceQuestion(List<MultipleChoiceQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) return;
        for (int i = 0; i < questions.size(); i++) {
            validateObject(questions.get(i));
        }
        questionRepository.saveAll(questions);
    }

    @Override
    public List<MultipleChoiceQuestion> getAllByIdTopicTest(Long idTopicTest) {
        return questionRepository.getAllByIdTopicTest(idTopicTest);
    }

    @Override
    public List<EssayQuestion> getAllEssayQuestionByIdTopicTest(Long idTopicTest) {
        return essayQuestionRepository.getAllByIdTopicTest(idTopicTest);
    }

    @Override
    public void createEssayQuestion(List<EssayQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) return;
        for (int i = 0; i < questions.size(); i++) {
            validateObject(questions.get(i));
        }
        essayQuestionRepository.saveAll(questions);
    }

    private void validateObject(MultipleChoiceQuestion question) {
        if (StringUtils.isEmpty(question.getContent()))
            throw new InvalidException("Câu hỏi số: " + question.getQuestionNumber() + " Không được để trống");
        if (StringUtils.isEmpty(question.getAnswer1()))
            throw new InvalidException("Đáp án A của câu hỏi: " + question.getQuestionNumber() + " Không được để trống");
        if (StringUtils.isEmpty(question.getAnswer2()))
            throw new InvalidException("Đáp án B của câu hỏi:" + question.getQuestionNumber() + " Không được để trống");
        if (StringUtils.isEmpty(question.getAnswer3()))
            throw new InvalidException("Đáp án C của câu hỏi:" + question.getQuestionNumber() + " Không được để trống");
        if (StringUtils.isEmpty(question.getAnswer4()))
            throw new InvalidException("Đáp án D của câu hỏi:" + question.getQuestionNumber() + " Không được để trống");
        if (StringUtils.isEmpty(question.getCorrectAnswer()))
            throw new InvalidException("Đáp án đúng của câu hỏi: " + question.getQuestionNumber() + " Không được để trống");
    }

    private void validateObject(EssayQuestion essayQuestion) {
        if (StringUtils.isEmpty(essayQuestion.getContent()))
            throw new InvalidException("Câu hỏi số: " + essayQuestion.getQuestionNumber() + " Không được để trống");
    }
}
