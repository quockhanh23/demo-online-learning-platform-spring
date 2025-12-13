package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.entities.Tuition;
import com.example.demoonlinelearningplatform.services.TuitionService;
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

    /**
     * Tạo mới thông tin học phí cần đóng tiền theo đợt
     *
     * @param idUsers: danh sách học sinh cần phải đóng học trong đợt
     * @param tuition: thông tin khác
     * @return void
     */
    @PostMapping("/createTuition")
    public ResponseEntity<Object> createTuition(@RequestParam List<Long> idUsers, @RequestBody Tuition tuition) {
        tuitionService.createPayment(idUsers, tuition);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Chi tiết thông tin học phí
     *
     * @param idUser:    id người dùng
     * @param idTuition: id của đối tượng Tuition
     * @return thông tin học phí
     */
    @GetMapping("/getDetailTuition")
    public ResponseEntity<Object> getDetailTuition(@RequestParam Long idUser, @RequestParam Long idTuition) {
        return new ResponseEntity<>(tuitionService.getDetailTuition(idTuition), HttpStatus.OK);
    }

    /**
     * Lấy ra danh sách học phí theo người dùng
     *
     * @param idUser: id người dùng
     * @return danh sách học phí tất cả các đợt của người dùng đó
     */
    @GetMapping("/getAllTuitionByIdUser")
    public ResponseEntity<Object> getAllTuitionByIdUser(@RequestParam Long idUser) {
        return new ResponseEntity<>(tuitionService.getAllTuitionByIdUser(idUser), HttpStatus.OK);
    }

    /**
     * Cập nhật thông tin học phí cần đóng tiền theo đợt
     *
     * @param idUser:    id người dùng
     * @param idTuition: id của đối tượng Tuition
     * @return void
     */
    @PutMapping("/updateTuition")
    public ResponseEntity<Object> updateTuition(@RequestParam Long idUser, @RequestParam Long idTuition) {
        tuitionService.confirmPayment(idUser, idTuition);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
