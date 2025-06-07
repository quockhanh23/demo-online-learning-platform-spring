package com.example.demoonlinelearningplatform.dto;

import com.example.demoonlinelearningplatform.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Date createdDate;
    private Date updatedDate;
    private String status;
    private Set<Role> roles;
}
