package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CourseRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idCourse;
    @NotNull
    private Long idUserRegister;
    @Column(length = 20)
    private String status;
    private Date createdDate = new Date();
}
