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
public class LessonLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idLesson;
    private String lessonName;
    private String sourceUpload;
    private String sourceUrl;
    private Long idUserAction;
    private Date createdDate;
    private Date updatedDate;
    @Column(length = 20)
    private String status;
}
