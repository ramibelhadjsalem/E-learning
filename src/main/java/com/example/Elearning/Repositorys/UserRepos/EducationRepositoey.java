package com.example.Elearning.Repositorys.UserRepos;

import com.example.Elearning.Models.UserModel.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepositoey extends JpaRepository<Education,Long> {


    @Query("select e from Education e where e.user.id = ?1")
    List<Education> findAllByUserId(Long id);


    boolean existsById(Long id);
}
