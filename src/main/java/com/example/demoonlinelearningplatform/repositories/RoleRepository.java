package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);
}
