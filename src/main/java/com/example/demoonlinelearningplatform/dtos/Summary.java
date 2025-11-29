package com.example.demoonlinelearningplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private String courseName;
    private List<String> scores;
    private String finalScore;
    private String status;
    private Date startDate;
}
