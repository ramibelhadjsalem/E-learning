package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Repositorys.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    public  boolean existByEamilorPhoneNumber(String phoneNumber,String eamil){
        return userRepository.existsByUsernameOrEmail(phoneNumber,eamil);
    }

    public Optional<User> findByUsername(String phoneNumber) {

        return userRepository.findByUsername(phoneNumber);

    }
    public boolean confirmed(String phoneNumber){

        return userRepository.existsByUsernameAndConfirmed(phoneNumber,true);
    }
}
