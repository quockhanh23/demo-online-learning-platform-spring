package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @Column(length = 300)
    private String testName;
    @Column(length = 20)
    // Đề 1 tiết hoặc các bài kiểm tra khác
    private String type;
    @Column(length = 20)
    private String status;
    @NotNull
    private Long idCourse;
    @NotNull
    private Long idLesson;
    @NotNull
    private Long idTeacher;
    private Date createDate = new Date();
}
