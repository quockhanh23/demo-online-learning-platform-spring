package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.entities.Tuition;

import java.util.List;

public interface TuitionService {

    void createPayment(List<Long> idUsers, Tuition tuition);

    void confirmPayment(Long idUser, Long idTuition);

    List<Tuition> getAllTuitionByIdUser(Long idUser);

    Tuition getDetailTuition(Long idTuition);
}
