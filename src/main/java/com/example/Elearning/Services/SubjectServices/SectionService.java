package com.example.Elearning.Services.SubjectServices;

import com.example.Elearning.Models.SectionModels.Section;
import com.example.Elearning.Repositorys.SubjectRepo.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository ;

    public List<Section> findAll(){
        return sectionRepository.findAll();
    }
    public Section findById(Long id){
        return sectionRepository.findById(id).get();
    }
    public Section save(Section section){
        return sectionRepository.save(section);
    }
    public void deleteByid(Long id ){sectionRepository.deleteById(id);}
    public boolean existsByName(String name){return sectionRepository.existsByName(name);}
    public Section findByName(String name){
        return sectionRepository.findByName(name).get();
    }
}
