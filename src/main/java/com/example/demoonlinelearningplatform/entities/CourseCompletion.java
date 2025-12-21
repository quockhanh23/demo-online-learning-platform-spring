package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Bảng hoàn thành khóa học
 * Khi người dùng học xong khóa học thì cập nhật
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCourse;
    private Long idUser;
    @Column(length = 20)
    private String status;
    private Date createdDate = new Date();
    private Date updatedDate;
}
