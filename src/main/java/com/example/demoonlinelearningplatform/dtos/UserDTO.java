package com.example.demoonlinelearningplatform.dtos;

import com.example.demoonlinelearningplatform.entities.Role;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Date createdDate;
    private Date updatedDate;
    private String status;
    private String education;
    private String avatar;
    private Set<Role> roles;
}
