package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.dtos.ChangePassword;
import com.example.demoonlinelearningplatform.entities.User;
import com.example.demoonlinelearningplatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * API này dùng để đăng nhập vào hệ thống
     *
     * @param user: thông tin người dùng cần nhập vào để đăng nhập vào hệ thống (username, password...)
     * @return thông tin người dùng
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    /**
     * API này dùng để lấy ra thông tin người dùng
     *
     * @param idUser: id của người dùng
     * @return thông tin người dùng
     */
    @GetMapping("/getDetailUser")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idUser) {
        return new ResponseEntity<>(userService.getDetailUser(idUser), HttpStatus.OK);
    }

    /**
     * API này dùng để cập nhật lại thông tin người dùng
     *
     * @param idUser: id của người dùng
     * @param user:   dữ liệu mới cần cập nhật
     * @return thông tin người dùng
     */
    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateInformation(@RequestParam Long idUser, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(idUser, user), HttpStatus.OK);
    }

    /**
     * Cập nhật lại mật khẩu mới
     *
     * @param idUser:         id của người dùng
     * @param changePassword: dữ liệu mật khẩu (mật khẩu mới, mật khẩu cũ...)
     * @return void
     */
    @PutMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestParam Long idUser, @RequestBody ChangePassword changePassword) {
        userService.changePassword(changePassword, idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
