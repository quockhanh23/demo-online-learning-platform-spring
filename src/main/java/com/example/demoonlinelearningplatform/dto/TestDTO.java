package com.example.demoonlinelearningplatform.dto;

import com.example.demoonlinelearningplatform.entity.EssayAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
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

}
