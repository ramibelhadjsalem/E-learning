package com.example.Elearning.Services.VideoServices;

import com.example.Elearning.Models.VideosModels.Video;
import com.example.Elearning.Repositorys.VideosRepo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository ;
    public VideoService() {
    }

    public List<Video> findAll(){
        return videoRepository.findAll();
    }
    public Video findById(Long id){
        try {
          return videoRepository.findById(id).get();
        }catch (Exception ex){
            throw new NotFoundException(" video with id :"+id+" Not found");
        }
    }
    public Video Save(Video video){
        return videoRepository.save(video);
    }
    public void deleteById(Long id){
        videoRepository.deleteById(id);
    }

    public List<Video> findByUserid(Long user_id){
        return videoRepository.findByUserId(user_id);
    }

}
