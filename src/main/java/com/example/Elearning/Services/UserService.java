package com.example.Elearning.Services;

import com.example.Elearning.Models.User;
import com.example.Elearning.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository ;

    public User saveUser(User user) {
            return userRepository.save(user);
    }
    public  boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}
