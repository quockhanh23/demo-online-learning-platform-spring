package com.example.demoonlinelearningplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MultipleChoiceQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long questionNumber;
    @NotNull
    private String content;
    @NotNull
    private String answer1;
    @NotNull
    private String answer2;
    @NotNull
    private String answer3;
    @NotNull
    private String answer4;
    @NotNull
    private String correctAnswer;
    @NotNull
    private String explainCorrectAnswer;
    @NotNull
    private Long idTopicTest;
    private Date createDate = new Date();
}
