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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 200)
    private String courseName;
    @NotNull
    @Column(length = 500)
    private String courseDescription;
    @Lob
    private String logo;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    private Date createdDate = new Date();
    private Date updatedDate;
    private Long idUser;
    @Column(length = 20)
    private String status;
    private String rate;
    private Long idDepartment;
}
