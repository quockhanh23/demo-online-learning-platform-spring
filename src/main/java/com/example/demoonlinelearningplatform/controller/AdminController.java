package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.dto.UserDTO;
import com.example.demoonlinelearningplatform.entity.User;
import com.example.demoonlinelearningplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUserPage(@RequestParam Long idUserLogin,
                                                 @RequestParam(defaultValue = "0", required = false) int page,
                                                 @RequestParam(defaultValue = "10", required = false) int size,
                                                 @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> userDTOS = userService.getAllUserPage(idUserLogin, pageable, searchText);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/getAllUserByRole")
    public ResponseEntity<Object> getAllUserByRole(@RequestParam Long idUserLogin,
                                                 @RequestParam(defaultValue = "0", required = false) int page,
                                                 @RequestParam(defaultValue = "10", required = false) int size,
                                                 @RequestParam(required = false) String role) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> userDTOS = userService.getAllUserPageByRole(pageable, role);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PutMapping("/action")
    public ResponseEntity<Object> actionUser(@RequestParam Long idAdmin,
                                             @RequestParam Long idUser,
                                             @RequestParam String action) {
        userService.actionUser(idAdmin, idUser, action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody User user, @RequestParam String role) {
        UserDTO userDTO = userService.createUser(user, role);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
