package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.entities.Department;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.DepartmentRepository;
import com.example.demoonlinelearningplatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final UserService userService;

    private final DepartmentRepository departmentRepository;

    /**
     * API này dùng để lấy ra toàn bộ danh sách bộ môn trong hệ thống theo các điều kiện
     *
     * @param idUserLogin: id của người đăng nhập
     * @param page:        trang hiện tại
     * @param size:        số phần tử trong 1 Trang
     * @param status:      tìm kiếm theo trạng thái
     * @param searchText:  tìm kiếm theo tên Department
     * @return danh sách người dùng trong hệ thống (phân trang)
     */
    @GetMapping("/getAllDepartment")
    public ResponseEntity<Object> getAllDepartment(@RequestParam Long idUserLogin,
                                                   @RequestParam(defaultValue = "0", required = false) int page,
                                                   @RequestParam(defaultValue = "10", required = false) int size,
                                                   @RequestParam(required = false) String status,
                                                   @RequestParam(required = false) String searchText) {
        userService.getDetailUser(idUserLogin);
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(departmentRepository.getAllDepartmentPage(pageable, searchText, status), HttpStatus.OK);
    }

    /**
     * Tạo mới bộ môn
     *
     * @param department:  department
     * @param idUserLogin: id của người đăng nhập
     * @return void
     */
    @PostMapping("/createDepartment")
    public ResponseEntity<Object> createDepartment(@RequestBody Department department, @RequestParam Long idUserLogin) {
        userService.getDetailUser(idUserLogin);
        department.setStatus(CommonConstant.ACTIVE);
        departmentRepository.save(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Cập nhật trạng thái bộ môn
     *
     * @param idUserLogin:  id của người đăng nhập
     * @param idDepartment: id của bộ môn thao tác đến
     * @param action:       trạng thái cập nhật
     * @return void
     */
    @PutMapping("/action")
    public ResponseEntity<Object> actionDepartment(@RequestParam Long idUserLogin, @RequestParam Long idDepartment,
                                                   @RequestParam String action) {
        userService.getDetailUser(idUserLogin);
        Optional<Department> departmentOptional = departmentRepository.findById(idDepartment);
        if (departmentOptional.isEmpty()) throw new InvalidException("Không tồn tại");
        departmentOptional.get().setStatus(action);
        departmentOptional.get().setUpdatedDate(new Date());
        departmentRepository.save(departmentOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
