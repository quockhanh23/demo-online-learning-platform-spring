package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.entities.EssayQuestion;
import com.example.demoonlinelearningplatform.entities.MultipleChoiceQuestion;
import com.example.demoonlinelearningplatform.entities.TopicTest;
import com.example.demoonlinelearningplatform.services.QuestionService;
import com.example.demoonlinelearningplatform.services.TopicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller này dùng để thao tác với đề bài kiểm tra
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/topicTests")
@RequiredArgsConstructor
public class TopicTestController {

    private final TopicTestService topicTestService;
    private final QuestionService questionService;

    /**
     * Tạo mới 1 bộ đề bài kiểm tra
     *
     * @param topicTest: thông tin đề bài kiểm tra
     * @return thông tin đề bài kiểm tra
     */
    @PostMapping("/createTopicTest")
    public ResponseEntity<Object> createTopicTest(@RequestBody TopicTest topicTest) {
        return new ResponseEntity<>(topicTestService.createTopicTest(topicTest), HttpStatus.CREATED);
    }

    /**
     * Lấy ra danh sách đề bài kiểm tra theo khóa học
     *
     * @param idCourse: id của khóa học
     * @return danh sách đề bài kiểm tra
     */
    @GetMapping("/getDetailTopicTestByCourse")
    public ResponseEntity<Object> getDetailTopicTestByCourse(@RequestParam Long idCourse) {
        return new ResponseEntity<>(topicTestService.getDetailTopicTestByCourse(idCourse), HttpStatus.OK);
    }

    /**
     * Lấy ra danh sách đề bài kiểm tra theo bài học
     *
     * @param idLesson: id của bài học
     * @return danh sách đề bài kiểm tra
     */
    @GetMapping("/getDetailTopicTestByLesson")
    public ResponseEntity<Object> getDetailTopicTestByLesson(@RequestParam Long idLesson) {
        return new ResponseEntity<>(topicTestService.getDetailTopicTestByLesson(idLesson), HttpStatus.OK);
    }

    /**
     * Tìm bài kiểm tra theo id
     *
     * @param idTopicTest: id của đề bài kiểm tra
     * @return thông tin của đề bài kiểm tra
     */
    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam Long idTopicTest) {
        return new ResponseEntity<>(topicTestService.findById(idTopicTest), HttpStatus.OK);
    }

    /**
     * Cập nhật đề bài kiểm tra
     *
     * @param topicTest: thông tin mới sẽ được cập nhật
     * @return thông tin của đề bài kiểm tra
     */
    @PutMapping("/updateTopicTest")
    public ResponseEntity<Object> updateTopicTest(@RequestBody TopicTest topicTest) {
        return new ResponseEntity<>(topicTestService.updateTopicTest(topicTest), HttpStatus.OK);
    }

    /**
     * Cập nhật trạng thái của đề bài kiểm tra
     *
     * @param status:      trạng của đề bài kiểm tra
     * @param idTopicTest: id của đề bài kiểm tra
     * @return thông tin của đề bài kiểm tra
     */
    @PutMapping("/updateStatusTopicTest")
    public ResponseEntity<Object> updateStatusTopicTest(@RequestParam String status, @RequestParam Long idTopicTest) {
        return new ResponseEntity<>(topicTestService.updateStatusTopicTest(status, idTopicTest), HttpStatus.OK);
    }

    /**
     * Tạo danh sách câu hỏi trắc nghiệm
     *
     * @param questions: danh sách câu hỏi trắc nghiệm
     * @return void
     */
    @PostMapping("/createMultipleChoiceQuestion")
    public ResponseEntity<Object> createMultipleChoiceQuestion(@RequestBody List<MultipleChoiceQuestion> questions) {
        questionService.createMultipleChoiceQuestion(questions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Tạo danh sách câu hỏi tự luận
     *
     * @param questions: danh sách câu hỏi tự luận
     * @return void
     */
    @PostMapping("/createEssayQuestion")
    public ResponseEntity<Object> createEssayQuestion(@RequestBody List<EssayQuestion> questions) {
        questionService.createEssayQuestion(questions);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Lấy ra danh sách câu hỏi trắc nghiệm theo đề bài kiểm tra
     *
     * @param idTopicTest: id của đề bài kiểm tra
     * @return danh sách câu hỏi trắc nghiệm
     */
    @GetMapping("/getMultipleChoiceQuestion")
    public ResponseEntity<Object> getMultipleChoiceQuestion(@RequestParam Long idTopicTest) {
        List<MultipleChoiceQuestion> questions = questionService.getAllByIdTopicTest(idTopicTest);
        if (CollectionUtils.isEmpty(questions)) questions = List.of();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * Lấy ra danh sách câu hỏi tự luận theo đề bài kiểm tra
     *
     * @param idTopicTest: id của đề bài kiểm tra
     * @return danh sách câu hỏi tự luận
     */
    @GetMapping("/getEssayQuestion")
    public ResponseEntity<Object> getEssayQuestion(@RequestParam Long idTopicTest) {
        List<EssayQuestion> questions = questionService.getAllEssayQuestionByIdTopicTest(idTopicTest);
        if (CollectionUtils.isEmpty(questions)) questions = List.of();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
