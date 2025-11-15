package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.entity.Tuition;
import com.example.demoonlinelearningplatform.repository.TuitionRepository;
import com.example.demoonlinelearningplatform.service.TuitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TuitionServiceImpl implements TuitionService {

    private final TuitionRepository tuitionRepository;

    @Override
    public void createPayment(List<Long> idUsers, Tuition tuitionRequest) {
        if (CollectionUtils.isEmpty(idUsers)) return;
        List<Tuition> tuitions = new ArrayList<>();
        for (Long idUser : idUsers) {
            Tuition tuition = new Tuition();
            tuition.setCreatedDate(new Date());
            tuition.setMoney(tuitionRequest.getMoney());
            tuition.setTitle(tuitionRequest.getTitle());
            tuition.setDescription(tuitionRequest.getDescription());
            tuition.setIdUser(idUser);
            tuition.setStatus("PROCESS");
            tuitions.add(tuition);
        }
        tuitionRepository.saveAll(tuitions);
    }

    public void confirmPayment(Long idStudent, Long idTuition) {
        Optional<Tuition> tuitionOptional = tuitionRepository.findById(idTuition);
        tuitionOptional.ifPresent(tuition -> tuition.setStatus("COMPLETE"));
    }

    @Override
    public List<Tuition> getAllTuitionByIdUser(Long idUser) {
        List<Tuition> tuitions = tuitionRepository.findAllByIdUser(idUser);
        if (CollectionUtils.isEmpty(tuitions)) return List.of();
        return tuitions;
    }

    @Override
    public Tuition getDetailTuition(Long idTuition) {
        Optional<Tuition> tuitionOptional = tuitionRepository.findById(idTuition);
        return tuitionOptional.get();
    }
}
