package com.example.demoonlinelearningplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CourseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCourse;
    private String courseName;
    private String courseDescription;
    private String logo;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;
    private Long idUserAction;
    private String status;
}
