package com.example.Elearning.Services.Userservices;

import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Repositorys.UserRepos.EducationRepositoey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class EducationService {
    @Autowired private EducationRepositoey educationRepositoey;
    public EducationService() {
    }

    public  List<Education> findallouwns(Long id){
        return  this.educationRepositoey.findAllByUserId(id);
    }
    public Education AddEducation(Education education){
        return  this.educationRepositoey.save(education);
    }
    public void deleteById(Long id){
        this.educationRepositoey.deleteById(id);
    }
    public  boolean Exsits(Long id){
        return  this.educationRepositoey.existsById(id);
    }
    public Education findById(Long id){
        try {
            return  this.educationRepositoey.findById(id).get();
        }catch (Error error){
            throw new NotFoundException(" education not found ");
        }
    }
}
