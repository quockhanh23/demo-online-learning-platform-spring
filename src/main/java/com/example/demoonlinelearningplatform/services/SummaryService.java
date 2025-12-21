package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.dtos.CompleteCourse;
import com.example.demoonlinelearningplatform.dtos.Summary;
import com.example.demoonlinelearningplatform.entities.LessonCompletion;

import java.util.List;

public interface SummaryService {

    List<Summary> coursesSummary(Long idUser);

    LessonCompletion checkCompleteLesson(Long idUser, Long idLesson);

    List<CompleteCourse> checkCompleteCourse(Long idUser);
}
