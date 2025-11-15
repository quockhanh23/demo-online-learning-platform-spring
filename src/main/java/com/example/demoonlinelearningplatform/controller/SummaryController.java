package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summaries")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/courseSummary")
    public ResponseEntity<Object> courseSummary(@RequestParam Long idUserLogin,
                                                @RequestParam(defaultValue = "0", required = false) int page,
                                                @RequestParam(defaultValue = "10", required = false) int size,
                                                @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(summaryService.coursesSummary(idUserLogin), HttpStatus.OK);
    }
}
