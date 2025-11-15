package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.dto.Summary;
import com.example.demoonlinelearningplatform.entity.Course;
import com.example.demoonlinelearningplatform.entity.CourseRegister;
import com.example.demoonlinelearningplatform.entity.Test;
import com.example.demoonlinelearningplatform.repository.TestRepository;
import com.example.demoonlinelearningplatform.service.CourseRegisterService;
import com.example.demoonlinelearningplatform.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final CourseRegisterService courseRegisterService;

    private final TestRepository testRepository;

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

            double sum = strings.stream()
                    .mapToDouble(Double::parseDouble)
                    .sum();
            sum = sum / strings.size();
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
}
