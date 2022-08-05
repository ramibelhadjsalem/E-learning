package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.Img;
import com.example.Elearning.Repositorys.UserRepos.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgService {
    @Autowired private ImgRepository imgRepository;
    public ImgService() {
    }

    public Img AddImg(Img img){
        return imgRepository.save(img);
    }
}
