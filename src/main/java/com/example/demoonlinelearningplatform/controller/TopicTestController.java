package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entity.TopicTest;
import com.example.demoonlinelearningplatform.service.QuestionService;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/topicTests")
@RequiredArgsConstructor
public class TopicTestController {

    private final TopicTestService topicTestService;
    private final QuestionService questionService;

    @PostMapping("/createTopicTest")
    public ResponseEntity<Object> createTopicTest(@RequestBody TopicTest topicTest) {
        return new ResponseEntity<>(topicTestService.createTopicTest(topicTest), HttpStatus.CREATED);
    }

    @GetMapping("/getDetailTopicTestByCourse")
    public ResponseEntity<Object> getDetailTopicTestByCourse(@RequestParam Long idCourse) {
        return new ResponseEntity<>(topicTestService.getDetailTopicTestByCourse(idCourse), HttpStatus.OK);
    }

    @GetMapping("/getDetailTopicTestByLesson")
    public ResponseEntity<Object> getDetailTopicTestByLesson(@RequestParam Long idLesson) {
        return new ResponseEntity<>(topicTestService.getDetailTopicTestByLesson(idLesson), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam Long idTopicTest) {
        return new ResponseEntity<>(topicTestService.findById(idTopicTest), HttpStatus.OK);
    }

    @PutMapping("/updateTopicTest")
    public ResponseEntity<Object> updateTopicTest(@RequestBody TopicTest topicTest) {
        return new ResponseEntity<>(topicTestService.updateTopicTest(topicTest), HttpStatus.OK);
    }

    @PutMapping("/updateStatusTopicTest")
    public ResponseEntity<Object> updateStatusTopicTest(@RequestParam String status, @RequestParam Long idTopicTest) {
        return new ResponseEntity<>(topicTestService.updateStatusTopicTest(status, idTopicTest), HttpStatus.OK);
    }

    @PostMapping("/createMultipleChoiceQuestion")
    public ResponseEntity<Object> createMultipleChoiceQuestion(@RequestBody List<MultipleChoiceQuestion> questions) {
        questionService.createMultipleChoiceQuestion(questions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createEssayQuestion")
    public ResponseEntity<Object> createEssayQuestion(@RequestBody List<EssayQuestion> questions) {
        questionService.createEssayQuestion(questions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getMultipleChoiceQuestion")
    public ResponseEntity<Object> getMultipleChoiceQuestion(@RequestParam Long idTopicTest) {
        List<MultipleChoiceQuestion> questions = questionService.getAllByIdTopicTest(idTopicTest);
        if (CollectionUtils.isEmpty(questions)) questions = List.of();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/getEssayQuestion")
    public ResponseEntity<Object> getEssayQuestion(@RequestParam Long idTopicTest) {
        List<EssayQuestion> questions = questionService.getAllEssayQuestionByIdTopicTest(idTopicTest);
        if (CollectionUtils.isEmpty(questions)) questions = List.of();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
