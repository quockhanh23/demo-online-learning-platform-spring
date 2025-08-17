package com.example.demoonlinelearningplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MultipleChoiceAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long questionNumber;
    private String answer;
    private Long idStudent;
    private Long idMultipleChoiceQuestion;
    private Long idTopicTest;
    private Long idLesson;
    private Long idTest;
    private Date createDate = new Date();
}
