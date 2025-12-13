package com.example.demoonlinelearningplatform.dtos;

import com.example.demoonlinelearningplatform.entities.EssayAnswer;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TestDTO {
    private Long id;
    private String testName;
    private String type;
    private String content;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private Long idStudent;
    private List<EssayAnswer> essayAnswerList;
    private List<MultipleChoiceAnswer> multipleChoiceAnswerList;
    private int time;
    private int score;
    private String scoreString;

}
