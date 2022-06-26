package com.example.Elearning.Services.SubjectServices;

import com.example.Elearning.Models.Subjects.Subject;
import com.example.Elearning.Repositorys.SubjectRepo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository ;

    public SubjectService() {
    }

    public  List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    public Subject findById(Long id){
        return subjectRepository.findById(id).get();
    }
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }
    public void deleteByid(Long id ){
        try {
            subjectRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("Not found Subject with id :"+id);
        }
    }
    public Subject findByName(String name){
        return subjectRepository.findByName(name).get();
    }
    public boolean existsbyName(String name){
        return subjectRepository.existsByName(name);
    }

}
