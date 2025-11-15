package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.entity.Tuition;

import java.util.List;

public interface TuitionService {

    void createPayment(List<Long> idUsers, Tuition tuition);

    void confirmPayment(Long idUser, Long idTuition);

    List<Tuition> getAllTuitionByIdUser(Long idUser);

    Tuition getDetailTuition(Long idTuition);
}
