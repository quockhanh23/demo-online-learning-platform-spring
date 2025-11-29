package com.example.demoonlinelearningplatform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    private Long idUser;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
