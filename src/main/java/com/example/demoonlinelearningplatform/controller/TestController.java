package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.TestDTO;
import com.example.demoonlinelearningplatform.dto.TopicTestDTO;
import com.example.demoonlinelearningplatform.entity.EssayAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entity.Test;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.EssayAnswerRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceAnswerRepository;
import com.example.demoonlinelearningplatform.repository.TestRepository;
import com.example.demoonlinelearningplatform.service.TestService;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final TopicTestService topicTestService;
    private final TestRepository testRepository;
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;
    private final EssayAnswerRepository essayAnswerRepository;

    @PostMapping("/createTest")
    public ResponseEntity<Object> createTest(@RequestBody Test test) {
        return new ResponseEntity<>(testService.createTest(test), HttpStatus.CREATED);
    }

    @PostMapping("/updateTime")
    public ResponseEntity<Object> updateTime(@RequestParam Long idTest, @RequestParam int time) {
        return new ResponseEntity<>(testService.updateTime(idTest, time), HttpStatus.OK);
    }

    @GetMapping("/getDetailTest")
    public ResponseEntity<Object> getDetailTest(@RequestParam Long idTest) {
        return new ResponseEntity<>(testService.getDetailTest(idTest), HttpStatus.OK);
    }

    @GetMapping("/getDetailTestByIdUserAndIdTopicTest")
    public ResponseEntity<Object> getDetailTestByIdUserAndIdTopicTest(@RequestParam Long idUser, @RequestParam Long idTopicTest) {
        return new ResponseEntity<>(testService.getDetailTestByIdUserAndIdTopicTest(idUser, idTopicTest), HttpStatus.OK);
    }

    @GetMapping("/getDetailTestByUserAndLesson")
    public ResponseEntity<Object> getDetailTestByUserAndLesson(@RequestParam Long idUser, @RequestParam Long idLesson) {
        List<TestDTO> testDTOS = testService.getAllTestByUserAndLesson(idUser, idLesson);
        return new ResponseEntity<>(testDTOS, HttpStatus.OK);
    }

    @PostMapping("/createMultipleChoiceAnswer")
    public ResponseEntity<Object> createMultipleChoiceAnswer(@RequestBody List<MultipleChoiceAnswer> answers) {
        if (CollectionUtils.isEmpty(answers)) throw new InvalidException("Danh sách trống");
        multipleChoiceAnswerRepository.saveAll(answers);
        TopicTestDTO topicTestDTO = topicTestService.getDetailTopicTestByLesson(answers.get(0).getIdLesson());
        List<MultipleChoiceQuestion> multipleChoiceQuestions = topicTestDTO.getMultipleChoiceQuestionList();
        int count = 0;
        for (MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions) {
            String correctAnswer = multipleChoiceQuestion.getCorrectAnswer();
            String answerOfStudent = answers.stream().filter(item -> item.getQuestionNumber() == multipleChoiceQuestion
                    .getQuestionNumber()).findFirst().map(MultipleChoiceAnswer::getAnswer).orElse(null);
            if (correctAnswer.equals(answerOfStudent)) {
                count = count + 1;
            }
        }
        Optional<Test> test = testRepository.findById(answers.get(0).getIdTest());
        if (test.isPresent()) {
            test.get().setScore(count);
            test.get().setScoreString(count + "/" + answers.size());
            testRepository.save(test.get());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createEssayAnswer")
    public ResponseEntity<Object> createEssayAnswer(@RequestBody List<EssayAnswer> answers) {
        essayAnswerRepository.saveAll(answers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
