package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    // Đánh giá giáo viên hoặc đánh giá khóa học
    private String type;
    @Column(length = 20)
    private String status;
    private int starLevel;
    @Column(length = 500)
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private Long idCourse;
    private Long idTeacher;
    private Long idUserAction;
}
