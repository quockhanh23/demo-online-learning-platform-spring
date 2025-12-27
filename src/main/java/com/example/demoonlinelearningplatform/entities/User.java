package com.example.demoonlinelearningplatform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    @NotBlank
    private String username;
    @Column(length = 40)
    @NotBlank
    private String password;
    @Column(length = 40)
    @NotBlank
    private String confirmPassword;
    @Column(length = 200)
    private String fullName;
    @Column(length = 200)
    private String email;
    @Column(length = 20)
    private String phoneNumber;
    private Date createdDate;
    private Date updatedDate;
    @Column(length = 20)
    private String status;
    @Column(length = 200)
    private String education;
    @Lob
    private String avatar;
    private Long idDepartment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_course",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses;
}
