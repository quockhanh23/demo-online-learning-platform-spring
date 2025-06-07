package com.example.demoonlinelearningplatform.dto;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopicTestDTO {
    private Long id;
    private String testName;
    private String type;
    private String content;
    private String status;
    private Long idCourse;
    private Long idLesson;
    private Long idTeacher;
    private List<EssayQuestion> essayQuestionList;
    private List<MultipleChoiceQuestion> multipleChoiceQuestionList;
}
