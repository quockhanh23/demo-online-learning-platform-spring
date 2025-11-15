package com.example.demoonlinelearningplatform.entity;

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
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long lessonNumber;
    @Column(length = 200)
    private String lessonName;
    @Lob
    private String image;
    @Lob
    private String sourceUrl;
    private Date createdDate = new Date();
    private Date updatedDate;
    @Column(length = 20)
    private String status;
    private Long idCourse;

}
