package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.EssayAnswer;
import com.example.demoonlinelearningplatform.entity.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entity.Test;
import com.example.demoonlinelearningplatform.repository.EssayAnswerRepository;
import com.example.demoonlinelearningplatform.repository.MultipleChoiceAnswerRepository;
import com.example.demoonlinelearningplatform.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;
    private final EssayAnswerRepository essayAnswerRepository;

    @PostMapping("/createTest")
    public ResponseEntity<Object> createReview(@RequestBody Test test) {
        testService.createTest(test);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getDetailTest")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idTest) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateTest")
    public ResponseEntity<Object> updateInformation(@RequestBody Test test) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createMultipleChoiceAnswer")
    public ResponseEntity<Object> createMultipleChoiceAnswer(@RequestBody MultipleChoiceAnswer answer) {
        multipleChoiceAnswerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createEssayAnswer")
    public ResponseEntity<Object> createEssayAnswer(@RequestBody EssayAnswer answer) {
        essayAnswerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
