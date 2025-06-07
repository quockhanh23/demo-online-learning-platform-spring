package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping("/createReview")
    public ResponseEntity<Object> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getDetailReview")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idReview) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateReview")
    public ResponseEntity<Object> updateInformation(@RequestBody Review review) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
