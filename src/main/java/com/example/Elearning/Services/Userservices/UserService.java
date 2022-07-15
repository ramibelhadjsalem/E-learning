package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

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
    public List<User> findAll(){
       return userRepository.findAll();
    }

    public User findById(Long id_user) {
        try {
            return userRepository.findById(id_user).get();
        }catch(Exception ex){
            throw new NotFoundException("user with id :"+id_user+" not found");
        }
    }

    public  boolean exitsByEmailOrPhoneNumber(String email,String phoneNumber){
        return userRepository.existsByEmailOrPhoneNumber(email,phoneNumber);
    }
    public  boolean existsByPhoneNumber(String phoneNumber){
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

}
