package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.EssayQuestion;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entity.TopicTest;
import com.example.demoonlinelearningplatform.repository.EssayQuestionRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceQuestionRepository;
import com.example.demoonlinelearningplatform.service.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/topicTests")
@RequiredArgsConstructor
public class TopicTestController {

    private final TopicTestService topicTestService;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final EssayQuestionRepository essayQuestionRepository;

    @PostMapping("/createTopicTest")
    public ResponseEntity<Object> createReview(@RequestBody TopicTest topicTest) {
        topicTestService.createTopicTest(topicTest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getDetailTopicTest")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idTopicTest) {
        topicTestService.getDetailTopicTest(idTopicTest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateTopicTest")
    public ResponseEntity<Object> updateInformation(@RequestBody TopicTest topicTest) {
        return new ResponseEntity<>(topicTestService.updateTopicTest(topicTest), HttpStatus.OK);
    }

    @PostMapping("/createMultipleChoiceQuestion")
    public ResponseEntity<Object> createMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestion multipleChoiceQuestion) {
        multipleChoiceQuestionRepository.save(multipleChoiceQuestion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createEssayQuestion")
    public ResponseEntity<Object> createEssayQuestion(@RequestBody EssayQuestion essayQuestion) {
        essayQuestionRepository.save(essayQuestion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
