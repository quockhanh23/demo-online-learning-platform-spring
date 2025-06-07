package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {

    Page<Lesson> getAllLessonByCourse(Pageable pageable, Long idCourse, String searchText);

    Lesson createLesson(Lesson request, Long idUser);

    Lesson getDetailLesson(Long idLesson);

    Lesson updateLesson(Lesson request, Long idUser);
}
