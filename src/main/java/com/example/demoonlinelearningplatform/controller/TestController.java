package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.TestDTO;
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

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;
    private final EssayAnswerRepository essayAnswerRepository;

    @PostMapping("/createTest")
    public ResponseEntity<Object> createTest(@RequestBody Test test) {
        return new ResponseEntity<>(testService.createTest(test), HttpStatus.CREATED);
    }

    @GetMapping("/getDetailTest")
    public ResponseEntity<Object> getDetailTest(@RequestParam Long idTest) {
        return new ResponseEntity<>(testService.getDetailTest(idTest), HttpStatus.OK);
    }

    @GetMapping("/getDetailTestByUserAndLesson")
    public ResponseEntity<Object> getDetailTestByUserAndLesson(@RequestParam Long idUser, @RequestParam Long idLesson) {
        List<TestDTO> testDTOS = testService.getAllTestByUserAndLesson(idUser, idLesson);
        return new ResponseEntity<>(testDTOS, HttpStatus.OK);
    }

    @PostMapping("/createMultipleChoiceAnswer")
    public ResponseEntity<Object> createMultipleChoiceAnswer(@RequestBody List<MultipleChoiceAnswer> answers) {
        multipleChoiceAnswerRepository.saveAll(answers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createEssayAnswer")
    public ResponseEntity<Object> createEssayAnswer(@RequestBody List<EssayAnswer> answers) {
        essayAnswerRepository.saveAll(answers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
