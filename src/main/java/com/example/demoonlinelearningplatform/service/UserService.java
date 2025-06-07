package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.UserDTO;
import com.example.demoonlinelearningplatform.entity.User;

public interface UserService {

    UserDTO login(User user);

    UserDTO createUser(User user, String role);

    UserDTO getDetailUser(Long idUser);

    UserDTO updateUser(Long idUser, User user);
}
