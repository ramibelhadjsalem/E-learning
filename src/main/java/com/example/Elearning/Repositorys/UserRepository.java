package com.example.Elearning.Repositorys;

import com.example.Elearning.Models.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


    @Query("select (count(u) > 0) from User u where u.username = ?1 or u.email = ?2")
    boolean existsByUsernameOrEmail(String username,String email);

    @Query("select u from User u where u.username = ?1 or u.email = ?1")
    Optional<User> findByUsernameOrEmail(String username);


    @Query("select (count(u) > 0) from User u where u.username = ?1 and u.confirmed = ?2")
    boolean existsByUsernameAndConfirmed(String phoneNumber, boolean confirmed );
}
