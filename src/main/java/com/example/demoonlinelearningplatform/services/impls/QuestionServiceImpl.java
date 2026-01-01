package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.entities.EssayQuestion;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.EssayQuestionRepository;
import com.example.demoonlinelearningplatform.repositories.MultipleChoiceQuestionRepository;
import com.example.demoonlinelearningplatform.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final MultipleChoiceQuestionRepository questionRepository;
    private final EssayQuestionRepository essayQuestionRepository;

    @Override
    public void createMultipleChoiceQuestion(List<MultipleChoiceQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) return;
        for (MultipleChoiceQuestion question : questions) {
            validateObject(question);
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
        for (EssayQuestion question : questions) {
            validateObject(question);
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

        validateDuplicateAnswers(question);
    }


    private void validateDuplicateAnswers(MultipleChoiceQuestion question) {
        Set<String> answers = new HashSet<>();
        answers.add(normalize(question.getAnswer1()));
        answers.add(normalize(question.getAnswer2()));
        answers.add(normalize(question.getAnswer3()));
        answers.add(normalize(question.getAnswer4()));
        if (answers.size() < 4) {
            throw new InvalidException(
                    "Các đáp án của câu hỏi số: " + question.getQuestionNumber() + " không được trùng nhau"
            );
        }
        String correctAnswer = question.getCorrectAnswer();
        boolean check = answers.stream().anyMatch(item -> item.equals(correctAnswer));
        if (!check) {
            throw new InvalidException("Đáp án đúng phải trùng khớp với 1 trong 4 đáp án trên");
        }
    }

    private String normalize(String value) {
        return value.trim().toLowerCase();
    }

    private void validateObject(EssayQuestion essayQuestion) {
        if (StringUtils.isEmpty(essayQuestion.getContent()))
            throw new InvalidException("Câu hỏi số: " + essayQuestion.getQuestionNumber() + " Không được để trống");
    }
}
