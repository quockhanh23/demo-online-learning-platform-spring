package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
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
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String status;
    private Date createdDate = new Date();
    private Date updatedDate;
    @NotNull
    private Long idStudent;
    @NotNull
    private Long idLesson;
    private Long idCourse;
    private Long idTopicTest;
    private String studentName;
    private int score;
    private String scoreString;
    private int time = 30;
    private String type;
}
