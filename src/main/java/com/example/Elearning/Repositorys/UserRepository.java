package com.example.Elearning.Repositorys;

import com.example.Elearning.Models.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


    Boolean existsByEmail(String email) ;

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select (count(u) > 0) from User u where u.email = ?1 or u.phoneNumber = ?2")
    boolean existsByEmailOrPhoneNumber(String email ,String phoneNumber);

    @Query("select u from User u where u.email = ?1 or u.phoneNumber = ?1")
    Optional<User> findByEmailOrPhoneNumber(String username);

}
