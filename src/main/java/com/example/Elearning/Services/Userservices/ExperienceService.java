package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Models.UserModel.Experience;
import com.example.Elearning.Repositorys.UserRepos.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class ExperienceService {
    @Autowired private ExperienceRepository experienceRepository;
    public ExperienceService() {
    }

    public List<Experience> findAllOuwns(Long id){
        return  this.experienceRepository.findAllByUserId(id);
    }
    public Experience AddExperience(Experience experience){
        return  this.experienceRepository.save(experience);
    }
    public void  deleteById(Long id){
        this.experienceRepository.deleteById(id);
    }

    public boolean Exsits(Long id) {
        return  experienceRepository.existsById(id);
    }
    public Experience findById(Long id){
        try {
            return  this.experienceRepository.findById(id).get();
        }catch (Error error){
            throw new NotFoundException(" experience not found ");
        }
    }
}
