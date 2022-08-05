package com.example.Elearning.Repositorys.UserRepos;

import com.example.Elearning.Models.UserModel.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Img,Long> {
}
