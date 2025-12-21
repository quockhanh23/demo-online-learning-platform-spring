package com.example.demoonlinelearningplatform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteCourse {
    private Long idCourse;
    private int totalLesson;
    private int registerCompleteLesson;
    private String status;
}
