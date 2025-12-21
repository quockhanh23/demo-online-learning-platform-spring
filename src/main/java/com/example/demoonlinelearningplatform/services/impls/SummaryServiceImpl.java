package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.dtos.CompleteCourse;
import com.example.demoonlinelearningplatform.dtos.Summary;
import com.example.demoonlinelearningplatform.entities.*;
import com.example.demoonlinelearningplatform.repositories.CourseCompletionRepository;
import com.example.demoonlinelearningplatform.repositories.LessonCompletionRepository;
import com.example.demoonlinelearningplatform.repositories.LessonRepository;
import com.example.demoonlinelearningplatform.repositories.TestRepository;
import com.example.demoonlinelearningplatform.services.CourseRegisterService;
import com.example.demoonlinelearningplatform.services.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final CourseRegisterService courseRegisterService;

    private final TestRepository testRepository;

    private final LessonRepository lessonRepository;

    private final LessonCompletionRepository lessonCompletionRepository;

    private final CourseCompletionRepository courseCompletionRepository;

    @Override
    public List<Summary> coursesSummary(Long idUser) {
        List<Summary> summaryList = new ArrayList<>();
        List<Course> courseList = courseRegisterService.getAllRegisterCourse(idUser);
        List<CourseRegister> courseRegisters = courseRegisterService.getAllRegisterCourseByIdUser(idUser);

        if (CollectionUtils.isEmpty(courseList)) return List.of();

        List<Long> courseIds = courseList.stream().map(Course::getId).toList();

        List<Test> testList = testRepository.findAllByIdCourseIn(courseIds);

        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            Summary summary = new Summary();
            summary.setCourseName(courseList.get(i).getCourseName());
            List<String> strings = new ArrayList<>();

            testList.stream()
                    .filter(t -> t.getIdCourse().equals(course.getId()))
                    .map(test1 -> String.valueOf(test1.getScore()))
                    .forEach(strings::add);
            summary.setScores(strings);
            double sum = 0;
            if (!CollectionUtils.isEmpty(strings)) {
                sum = strings.stream()
                        .mapToDouble(Double::parseDouble)
                        .sum();
                sum = sum / strings.size();
            }
            summary.setFinalScore(String.valueOf(sum));

            courseRegisters.stream().filter(t -> t.getIdCourse().equals(course.getId()))
                    .findFirst().ifPresent(courseRegister -> {
                        summary.setStatus(courseRegister.getStatus());
                        summary.setStartDate(courseRegister.getCreatedDate());
                    });
            summaryList.add(summary);
        }
        return summaryList;
    }

    @Override
    public LessonCompletion checkCompleteLesson(Long idUser, Long idLesson) {
        List<Test> testList = testRepository.getAllTestByIdStudentAndIdLesson(idUser, idLesson);
        if (!CollectionUtils.isEmpty(testList)) {
            Optional<LessonCompletion> lessonCompletion = lessonCompletionRepository.findByIdLesson(idLesson);
            if (lessonCompletion.isEmpty()) {
                LessonCompletion completion = new LessonCompletion();
                completion.setIdLesson(idLesson);
                completion.setIdUser(idUser);
                completion.setStatus(CommonConstant.COMPLETE);
                lessonCompletionRepository.save(completion);
                return completion;
            } else {
                lessonCompletion.get().setStatus(CommonConstant.COMPLETE);
                lessonCompletion.get().setUpdatedDate(new Date());
                lessonCompletionRepository.save(lessonCompletion.get());
                return lessonCompletion.get();
            }
        } else {
            LessonCompletion completion = new LessonCompletion();
            completion.setIdLesson(idLesson);
            completion.setIdUser(idUser);
            completion.setStatus(CommonConstant.STUDYING);
            lessonCompletionRepository.save(completion);
            return completion;
        }
    }

    @Override
    public List<CompleteCourse> checkCompleteCourse(Long idUser) {
        List<CompleteCourse> completeCourses = new ArrayList<>();
        // Bước 1: Lấy ra toàn bộ khóa học mà người dùng đã đăng kí
        List<Course> courseList = courseRegisterService.getAllRegisterCourse(idUser);

        List<CourseCompletion> completionsInDataBase = courseCompletionRepository.findAllByIdUser(idUser);
        if (!CollectionUtils.isEmpty(courseList)) {

            List<Long> idCourses = courseList.stream().map(Course::getId).toList();
            // Lấy ra toàn bộ bài học của tất cả cá khóa học
            List<Lesson> lessons = lessonRepository.findAllByIdCourseIn(idCourses);
            // Lấy ra toàn bộ bài học của tất cả cá khóa học đã đăng kí
            List<LessonCompletion> lessonCompletions = lessonCompletionRepository.findAllByIdCourseIn(idCourses);

            List<CourseCompletion> courseCompletions = new ArrayList<>();
            for (Long idCourse : idCourses) {
                List<Lesson> lessonList = lessons.stream()
                        .filter(item -> item.getIdCourse().equals(idCourse)).toList();
                List<LessonCompletion> lessonCompletionList = lessonCompletions.stream()
                        .filter(item -> item.getIdCourse().equals(idCourse)).toList();

                CompleteCourse completeCourse = new CompleteCourse();
                completeCourse.setIdCourse(idCourse);
                completeCourse.setTotalLesson(lessonList.size());
                completeCourse.setRegisterCompleteLesson(lessonCompletionList.size());
                if (completeCourse.getTotalLesson() == completeCourse.getRegisterCompleteLesson()) {
                    completeCourse.setStatus(CommonConstant.COMPLETE);
                } else {
                    completeCourse.setStatus(CommonConstant.STUDYING);
                }
                completeCourses.add(completeCourse);

                CourseCompletion completionDB = completionsInDataBase.stream()
                        .filter(item -> item.getIdCourse().equals(idCourse)
                                && item.getIdUser().equals(idUser)).findFirst().orElse(null);
                if (Objects.nonNull(completionDB)) {
                    completionDB.setStatus(completeCourse.getStatus());
                    completionDB.setUpdatedDate(new Date());
                    courseCompletions.add(completionDB);
                } else {
                    CourseCompletion completion = new CourseCompletion();
                    completion.setIdCourse(idCourse);
                    completion.setStatus(completeCourse.getStatus());
                    completion.setIdUser(idUser);
                    courseCompletions.add(completion);
                }
            }
            if (!CollectionUtils.isEmpty(courseCompletions)) {
                courseCompletionRepository.saveAll(courseCompletions);
            }
        }
        return completeCourses;
    }
}
