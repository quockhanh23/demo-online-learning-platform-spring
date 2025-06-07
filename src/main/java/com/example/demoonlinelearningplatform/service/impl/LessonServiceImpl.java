package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.entity.Lesson;
import com.example.demoonlinelearningplatform.entity.LessonLog;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.LessonLogRepository;
import com.example.demoonlinelearningplatform.repository.LessonRepository;
import com.example.demoonlinelearningplatform.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonLogRepository lessonLogRepository;

    @Override
    public Page<Lesson> getAllLessonByCourse(Pageable pageable, Long idCourse, String searchText) {
        Page<Lesson> lessonList;
        if (StringUtils.isEmpty(searchText)) {
            lessonList = lessonRepository.getAllLessonByCoursePage(pageable, idCourse);
        } else {
            lessonList = lessonRepository.getAllLessonByCoursePage(pageable, searchText, idCourse);
        }
        return lessonList;
    }

    @Override
    @Transactional
    public Lesson createLesson(Lesson request, Long idUser) {
        request.setCreatedDate(new Date());
        Lesson lesson = lessonRepository.save(request);
        createLessonLog(lesson, idUser);
        return lesson;
    }

    @Override
    public Lesson getDetailLesson(Long idLesson) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(idLesson);
        if (lessonOptional.isEmpty()) throw new InvalidException("Không tìm thấy");
        return lessonOptional.get();
    }

    @Override
    @Transactional
    public Lesson updateLesson(Lesson request, Long idUser) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(request.getId());
        if (lessonOptional.isPresent()) {
            lessonOptional.get().setLessonName(request.getLessonName());
            lessonOptional.get().setUpdatedDate(new Date());
            createLessonLog(request, idUser);
            return lessonRepository.save(lessonOptional.get());
        }
        return null;
    }

    private void createLessonLog(Lesson request, Long idUser) {
        LessonLog lessonLog = new LessonLog();
        lessonLog.setLessonName(request.getLessonName());
        lessonLog.setCreatedDate(new Date());
        lessonLog.setUpdatedDate(new Date());
        lessonLog.setIdLesson(request.getId());
        lessonLog.setIdUserAction(idUser);
        lessonLogRepository.save(lessonLog);
    }
}
