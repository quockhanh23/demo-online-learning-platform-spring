package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;

import java.util.List;

public interface QuestionService {

    void createMultipleChoiceQuestion(List<MultipleChoiceQuestion> questions);

    List<MultipleChoiceQuestion> getAllByIdTopicTest(Long idTopicTest);

    List<EssayQuestion> getAllEssayQuestionByIdTopicTest(Long idTopicTest);

    void createEssayQuestion(List<EssayQuestion> questions);
}
