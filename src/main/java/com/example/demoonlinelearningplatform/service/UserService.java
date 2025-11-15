package com.example.demoonlinelearningplatform.service;

import com.example.demoonlinelearningplatform.dto.UserDTO;
import com.example.demoonlinelearningplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO login(User user);

    UserDTO createUser(User user, String role);

    UserDTO getDetailUser(Long idUser);

    UserDTO updateUser(Long idUser, User user);

    Page<UserDTO> getAllUserPage(Long idUserLogin, Pageable pageable, String searchText);

    Page<UserDTO> getAllUserPageByRole(Pageable pageable, String role);

    void actionUser(Long idAdmin, Long idUser, String action);
}
