package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.entity.Tuition;
import com.example.demoonlinelearningplatform.service.TuitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tuitions")
@RequiredArgsConstructor
public class TuitionController {

    private final TuitionService tuitionService;

    @PostMapping("/createTuition")
    public ResponseEntity<Object> createTuition(@RequestParam List<Long> idUsers, @RequestBody Tuition tuition) {
        tuitionService.createPayment(idUsers, tuition);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDetailTuition")
    public ResponseEntity<Object> getDetailTuition(@RequestParam Long idUser, @RequestParam Long idTuition) {
        return new ResponseEntity<>(tuitionService.getDetailTuition(idTuition), HttpStatus.OK);
    }

    @GetMapping("/getAllTuitionByIdUser")
    public ResponseEntity<Object> getAllTuitionByIdUser(@RequestParam Long idUser) {
        return new ResponseEntity<>(tuitionService.getAllTuitionByIdUser(idUser), HttpStatus.OK);
    }

    @PutMapping("/updateTuition")
    public ResponseEntity<Object> updateTuition(@RequestParam Long idUser, @RequestParam Long idTuition) {
        tuitionService.confirmPayment(idUser, idTuition);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
