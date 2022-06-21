package com.example.Elearning.Repositorys;

import com.example.Elearning.Models.ERole;
import com.example.Elearning.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRpository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
