package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.MultipleChoice;
import com.example.demoonlinelearningplatform.dto.ReviewResults;
import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.service.TestService;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviewResults")
@RequiredArgsConstructor
public class ReviewResultsController {

    private final TestService testService;
    private final TopicTestService topicTestService;

    @GetMapping("/getReviewResults")
    public ResponseEntity<Object> getReviewResults(@RequestParam Long idTest, @RequestParam Long idLesson) {
        TestDTO testDTOS = testService.getDetailTest(idTest);
        TopicTestDTO topicTestDTO = topicTestService.getDetailTopicTestByLesson(idLesson);
        ReviewResults reviewResults = new ReviewResults();
        int correctAnswer = 0;
        for (int i = 0; i < topicTestDTO.getMultipleChoiceQuestionList().size(); i++) {
            MultipleChoiceQuestion choiceQuestion = topicTestDTO.getMultipleChoiceQuestionList().get(i);
            MultipleChoice multipleChoice = new MultipleChoice();
            BeanUtils.copyProperties(topicTestDTO.getMultipleChoiceQuestionList().get(i), multipleChoice);
            multipleChoice.setQuestion(topicTestDTO.getMultipleChoiceQuestionList().get(i).getContent());
            MultipleChoiceAnswer multipleChoiceAnswer = testDTOS.getMultipleChoiceAnswerList()
                    .stream()
                    .filter(item -> item.getQuestionNumber() == choiceQuestion.getQuestionNumber())
                    .findFirst().orElse(null);

            multipleChoice.setAnswerOfStudent(Objects.isNull(multipleChoiceAnswer) ? "" : multipleChoiceAnswer.getAnswer());
            reviewResults.getMultipleChoices().add(multipleChoice);
            if (multipleChoice.getCorrectAnswer().equals(multipleChoice.getAnswerOfStudent())) correctAnswer = correctAnswer + 1;
        }
        reviewResults.setTotalCorrectAnswer(correctAnswer);
        return new ResponseEntity<>(reviewResults, HttpStatus.OK);
    }
}
