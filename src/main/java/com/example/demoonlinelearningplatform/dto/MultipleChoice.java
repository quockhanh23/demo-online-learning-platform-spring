package com.example.demoonlinelearningplatform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleChoice {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private String explainCorrectAnswer;
    private String answerOfStudent;
    private long questionNumber;

}
