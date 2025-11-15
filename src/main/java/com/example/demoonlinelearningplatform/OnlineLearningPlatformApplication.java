package com.example.demoonlinelearningplatform;

import com.example.demoonlinelearningplatform.entity.Role;
import com.example.demoonlinelearningplatform.entity.User;
import com.example.demoonlinelearningplatform.repository.RoleRepository;
import com.example.demoonlinelearningplatform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SpringBootApplication
public class OnlineLearningPlatformApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(OnlineLearningPlatformApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository,
                                 RoleRepository roleRepository) {
        return args ->
        {
            if (roleRepository.findByRoleName("ADMIN") == null) {
                Role role = new Role();
                role.setRoleName("ADMIN");
                roleRepository.save(role);
            }
            if (roleRepository.findByRoleName("STUDENT") == null) {
                Role role = new Role();
                role.setRoleName("STUDENT");
                roleRepository.save(role);
            }
            if (roleRepository.findByRoleName("TEACHER") == null) {
                Role role = new Role();
                role.setRoleName("TEACHER");
                roleRepository.save(role);
            }

            User admin = userRepository.findUserByUsername("admin");
            if (Objects.isNull(admin)) {
                User user = new User();
                Set<Role> roles = new HashSet<>();
                Role role = roleRepository.findByRoleName("ADMIN");
                roles.add(role);
                user.setRoles(roles);
                user.setUsername("admin");
                user.setPassword("admin");
                user.setConfirmPassword("admin");
                user.setFullName("admin");
                user.setCreatedDate(new Date());
                user.setUpdatedDate(new Date());
                user.setStatus("ACTIVE");
                userRepository.save(user);
            }
        };
    }
}
