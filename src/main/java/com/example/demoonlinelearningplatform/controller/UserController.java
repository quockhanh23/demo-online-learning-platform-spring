package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.ChangePassword;
import com.example.demoonlinelearningplatform.dto.UserDTO;
import com.example.demoonlinelearningplatform.entity.User;
import com.example.demoonlinelearningplatform.service.UserService;
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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @GetMapping("/getDetailUser")
    public ResponseEntity<Object> getDetailUser(@RequestParam Long idUser) {
        return new ResponseEntity<>(userService.getDetailUser(idUser), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateInformation(@RequestParam Long idUser, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(idUser, user), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestParam Long idUser, @RequestBody ChangePassword changePassword) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
