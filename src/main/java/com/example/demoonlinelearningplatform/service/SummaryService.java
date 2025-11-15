package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.Summary;

import java.util.List;

public interface SummaryService {

    List<Summary> coursesSummary(Long idUser);
}
