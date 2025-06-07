package com.example.demoonlinelearningplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TopicTest {
    // Đề bài kiểm tra
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testName;
    private String type;
    private String content;
    private String status;
    private Long idCourse;
    private Long idLesson;
    private Long idTeacher;
}
