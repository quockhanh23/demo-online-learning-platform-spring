package com.example.demoonlinelearningplatform.services;

import com.example.demoonlinelearningplatform.dtos.ChangePassword;
import com.example.demoonlinelearningplatform.dtos.UserDTO;
import com.example.demoonlinelearningplatform.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO login(User user);

    UserDTO createUser(User user, String role);

    UserDTO getDetailUser(Long idUser);

    UserDTO updateUser(Long idUser, User user);

    Page<UserDTO> getAllUserPage(Long idUserLogin, Pageable pageable, String searchText);

    Page<UserDTO> getAllUserPageByRole(Pageable pageable, String role);

    Page<UserDTO> getAllUserByDepartment(Pageable pageable, Long idDepartment);

    void actionUser(Long idAdmin, Long idUser, String action);

    void changePassword(ChangePassword changePassword, Long idUser);
}
