package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
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
public class LessonCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idLesson;
    private Long idCourse;
    private Long idUser;
    private String timeLearned;
    @Column(length = 20)
    private String status;
    private Date createdDate = new Date();
    private Date updatedDate;
}
