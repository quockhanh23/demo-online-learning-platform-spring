package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.dtos.MultipleChoice;
import com.example.demoonlinelearningplatform.dtos.ReviewResults;
import com.example.demoonlinelearningplatform.dtos.TestDTO;
import com.example.demoonlinelearningplatform.dtos.TopicTestDTO;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.services.TestService;
import com.example.demoonlinelearningplatform.services.TopicTestService;
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

    /**
     * Xem lại toàn bộ số điểm của 1 bài học
     *
     * @param idTest:   id của bài kiểm tra
     * @param idLesson: id của bài học
     * @return 1 đối tượng chứa các kết quả của bài kiểm tra
     */
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
            if (multipleChoice.getCorrectAnswer().equals(multipleChoice.getAnswerOfStudent()))
                correctAnswer = correctAnswer + 1;
        }
        reviewResults.setTotalCorrectAnswer(correctAnswer);
        return new ResponseEntity<>(reviewResults, HttpStatus.OK);
    }
}
