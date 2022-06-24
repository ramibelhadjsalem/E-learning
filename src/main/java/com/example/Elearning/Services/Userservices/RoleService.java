package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Repositorys.RoleRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRpository roleRpository  ;

    public Optional<Role> findByName(ERole name){
        return roleRpository.findByName(name);
    }
}
