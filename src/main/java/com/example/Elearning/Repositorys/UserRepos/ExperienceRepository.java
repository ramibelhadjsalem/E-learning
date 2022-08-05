package com.example.Elearning.Repositorys.UserRepos;

import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Models.UserModel.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    List<Experience> findAllByUserId(Long id);
}
