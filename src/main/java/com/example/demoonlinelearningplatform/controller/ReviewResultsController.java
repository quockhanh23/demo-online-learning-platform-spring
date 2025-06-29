package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.MultipleChoice;
import com.example.demoonlinelearningplatform.dto.ReviewResults;
import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.service.QuestionService;
import com.example.demoonlinelearningplatform.service.TestService;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviewResults")
@RequiredArgsConstructor
public class ReviewResultsController {

    private final TestService testService;
    private final TopicTestService topicTestService;
    private final QuestionService questionService;

    @GetMapping("/getReviewResults")
    public ResponseEntity<Object> getDetailTestByUserAndLesson(@RequestParam Long idTest, @RequestParam Long idLesson) {
        TestDTO testDTOS = testService.getDetailTest(idTest);
        TopicTestDTO topicTestDTO = topicTestService.getDetailTopicTestByLesson(idLesson);
        ReviewResults reviewResults = new ReviewResults();
        for (int i = 0; i < topicTestDTO.getMultipleChoiceQuestionList().size(); i++) {
            MultipleChoiceQuestion choiceQuestion = topicTestDTO.getMultipleChoiceQuestionList().get(i);
            MultipleChoice multipleChoice = new MultipleChoice();
            multipleChoice.setAnswer1(topicTestDTO.getMultipleChoiceQuestionList().get(i).getAnswer1());
            multipleChoice.setAnswer2(topicTestDTO.getMultipleChoiceQuestionList().get(i).getAnswer2());
            multipleChoice.setAnswer3(topicTestDTO.getMultipleChoiceQuestionList().get(i).getAnswer3());
            multipleChoice.setAnswer4(topicTestDTO.getMultipleChoiceQuestionList().get(i).getAnswer4());
            multipleChoice.setCorrectAnswer(topicTestDTO.getMultipleChoiceQuestionList().get(i).getCorrectAnswer());
            multipleChoice.setExplainCorrectAnswer(topicTestDTO.getMultipleChoiceQuestionList().get(i).getExplainCorrectAnswer());
            multipleChoice.setQuestion(topicTestDTO.getMultipleChoiceQuestionList().get(i).getContent());

            MultipleChoiceAnswer multipleChoiceAnswer = testDTOS.getMultipleChoiceAnswerList()
                    .stream()
                    .filter(item -> item.getQuestionNumber() == choiceQuestion.getQuestionNumber())
                    .findFirst().orElse(null);

            multipleChoice.setAnswerOfStudent(Objects.isNull(multipleChoiceAnswer) ? "" : multipleChoiceAnswer.getAnswer());
            reviewResults.getMultipleChoices().add(multipleChoice);
        }

        return new ResponseEntity<>(reviewResults, HttpStatus.OK);
    }
}
