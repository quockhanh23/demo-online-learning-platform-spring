package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
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
    private Date createdDate = new Date();
    private Date updatedDate;
    private Long idUserAction;
    @Column(length = 20)
    private String status;
}
