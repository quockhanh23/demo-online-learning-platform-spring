package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.dtos.Summary;

import java.util.List;

public interface SummaryService {

    List<Summary> coursesSummary(Long idUser);
}
