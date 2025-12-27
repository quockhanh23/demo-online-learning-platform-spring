package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
public class EssayAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answerEssay;
    @NotNull
    private Long idStudent;
    private Long idEssayQuestion;
    private Long idTopicTest;
    @NotNull
    private Long idLesson;
    @NotNull
    private Long idTest;
    private Date createDate = new Date();
}
