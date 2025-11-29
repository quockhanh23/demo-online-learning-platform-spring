package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.entities.EssayQuestion;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;

import java.util.List;

public interface QuestionService {

    void createMultipleChoiceQuestion(List<MultipleChoiceQuestion> questions);

    List<MultipleChoiceQuestion> getAllByIdTopicTest(Long idTopicTest);

    List<EssayQuestion> getAllEssayQuestionByIdTopicTest(Long idTopicTest);

    void createEssayQuestion(List<EssayQuestion> questions);
}
