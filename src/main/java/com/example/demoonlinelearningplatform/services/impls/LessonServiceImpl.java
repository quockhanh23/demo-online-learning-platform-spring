package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.entities.Lesson;
import com.example.demoonlinelearningplatform.entities.LessonLog;
import com.example.demoonlinelearningplatform.entities.Test;
import com.example.demoonlinelearningplatform.entities.User;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.LessonLogRepository;
import com.example.demoonlinelearningplatform.repositories.LessonRepository;
import com.example.demoonlinelearningplatform.repositories.TestRepository;
import com.example.demoonlinelearningplatform.repositories.UserRepository;
import com.example.demoonlinelearningplatform.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonLogRepository lessonLogRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

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
    public List<Lesson> getAllLessonByCourseList(Long idCourse, String searchText) {
        return lessonRepository.findByIdCourse(idCourse, searchText);
    }

    @Override
    @Transactional
    public Lesson createLesson(Lesson request, Long idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Người dùng không tồn tại");
        validateLesson(request);
        int count = lessonRepository.countAllByIdCourse(request.getIdCourse());
        request.setStatus("ACTIVE");
        request.setLessonNumber(count + 1);
        Lesson lesson = lessonRepository.save(request);
        createLessonLog(lesson, idUser);
        return lesson;
    }

    void validateLesson(Lesson request) {
        if (request.getLessonName().length() > 200) throw new InvalidException("Tên bài học không quá 200 kí tự");
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
        if (lessonOptional.isEmpty()) {
            throw new InvalidException("Không tìm thấy");
        }
        lessonOptional.get().setLessonName(request.getLessonName());
        lessonOptional.get().setUpdatedDate(new Date());
        createLessonLog(request, idUser);
        return lessonRepository.save(lessonOptional.get());
    }

    @Override
    public Test highestPointLesson(Long idLesson, Long idUser) {
        List<Test> testDTOS = testRepository.getAllTestByIdStudentAndIdLesson(idUser, idLesson);
        Test test = testDTOS.stream()
                .max(Comparator.comparingInt(Test::getScore))
                .orElse(null);
        return test;
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
