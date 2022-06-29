package com.example.Elearning.Repositorys.VideosRepo;

import com.example.Elearning.Models.VideosModels.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

    @Query("select v from Video v where v.user.id = ?1")
    List<Video> findByUserId(Long user_Id);
}
