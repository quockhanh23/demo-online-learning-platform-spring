package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Lesson;
import com.example.demoonlinelearningplatform.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

    Page<Lesson> getAllLessonByCourse(Pageable pageable, Long idCourse, String searchText);

    List<Lesson> getAllLessonByCourseList(Long idCourse, String searchText);

    Lesson createLesson(Lesson request, Long idUser);

    Lesson getDetailLesson(Long idLesson);

    Lesson updateLesson(Lesson request, Long idUser);

    Test highestPointLesson(Long idLesson, Long idUser);
}
