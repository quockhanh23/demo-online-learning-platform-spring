package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.commons.Common;
import com.example.demoonlinelearningplatform.dtos.TestDTO;
import com.example.demoonlinelearningplatform.dtos.TopicTestDTO;
import com.example.demoonlinelearningplatform.entities.EssayAnswer;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceAnswer;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entities.Test;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.EssayAnswerRepository;
import com.example.demoonlinelearningplatform.repositories.MultipleChoiceAnswerRepository;
import com.example.demoonlinelearningplatform.repositories.TestRepository;
import com.example.demoonlinelearningplatform.services.TestService;
import com.example.demoonlinelearningplatform.services.TopicTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
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

    /**
     * Tạo mới 1 bài kiểm tra
     *
     * @param test: thông tin bài kiểm tra
     * @return thông tin bài kiểm tra vừa được tạo
     */
    @PostMapping("/createTest")
    public ResponseEntity<Object> createTest(@Valid @RequestBody Test test, BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
        return new ResponseEntity<>(testService.createTest(test), HttpStatus.CREATED);
    }

    /**
     * Lưu thông tin thời gian làm bài còn lại
     *
     * @param idTest: id của bài kiểm tra
     * @param time:   số thời gian còn lại
     * @return thông tin bài kiểm tra
     */
    @PostMapping("/updateTime")
    public ResponseEntity<Object> updateTime(@RequestParam Long idTest, @RequestParam int time) {
        return new ResponseEntity<>(testService.updateTime(idTest, time), HttpStatus.OK);
    }

    /**
     * Lấy ra thông tin của bài kiểm tra
     *
     * @param idTest: id của bài kiểm tra
     * @return thông tin của bài kiểm tra
     */
    @GetMapping("/getDetailTest")
    public ResponseEntity<Object> getDetailTest(@RequestParam Long idTest) {
        return new ResponseEntity<>(testService.getDetailTest(idTest), HttpStatus.OK);
    }

    /**
     * Lấy ra thông tin của bài kiểm tra của người dùng và theo đề bài
     *
     * @param idUser:      id của người dùng
     * @param idTopicTest: id của đề bài kiểm tra
     * @return thông tin của bài kiểm tra
     */
    @GetMapping("/getDetailTestByIdUserAndIdTopicTest")
    public ResponseEntity<Object> getDetailTestByIdUserAndIdTopicTest(@RequestParam Long idUser, @RequestParam Long idTopicTest) {
        return new ResponseEntity<>(testService.getDetailTestByIdUserAndIdTopicTest(idUser, idTopicTest), HttpStatus.OK);
    }

    /**
     * Lấy ra thông tin của bài kiểm tra của người dùng và theo bài học
     *
     * @param idUser:   id của người dùng
     * @param idLesson: id của bài học
     * @return thông tin của bài kiểm tra
     */
    @GetMapping("/getDetailTestByUserAndLesson")
    public ResponseEntity<Object> getDetailTestByUserAndLesson(@RequestParam Long idUser, @RequestParam Long idLesson) {
        List<TestDTO> testDTOS = testService.getAllTestByUserAndLesson(idUser, idLesson);
        return new ResponseEntity<>(testDTOS, HttpStatus.OK);
    }

    /**
     * Lưu lại các câu trả lời trắc nghiệm
     *
     * @param answers: danh sách các câu trả lời trắc nghiệm
     * @return void
     */
    @PostMapping("/createMultipleChoiceAnswer")
    public ResponseEntity<Object> createMultipleChoiceAnswer(@Valid @RequestBody List<MultipleChoiceAnswer> answers,
                                                             BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
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

    /**
     * Lưu lại các câu trả lời tự luận
     *
     * @param answers: danh sách các câu trả lời tự luận
     * @return void
     */
    @PostMapping("/createEssayAnswer")
    public ResponseEntity<Object> createEssayAnswer(@Valid @RequestBody List<EssayAnswer> answers,
                                                    BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
        if (!CollectionUtils.isEmpty(answers)) {
            essayAnswerRepository.saveAll(answers);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
