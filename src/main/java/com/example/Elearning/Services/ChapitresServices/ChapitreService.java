package com.example.Elearning.Services.ChapitresServices;

import com.example.Elearning.Models.ChapitresModels.Chapitre;
import com.example.Elearning.Repositorys.ChapitresRepo.ChapitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class ChapitreService {

    @Autowired
    ChapitreRepository  chapitreRepository;
    public ChapitreService() {
    }

    public List<Chapitre> findAll(){
        return chapitreRepository.findAll();
    }
    public Chapitre findById(Long id){
        try {
            return chapitreRepository.findById(id).get();
        }catch (Exception ex){
            throw new NotFoundException("chapitre with id :"+id+" Not found");
        }

    }
    public Chapitre Save(Chapitre chapitre){
        return chapitreRepository.save(chapitre);
    }
    public void deleteById(Long id){
        chapitreRepository.deleteById(id);
    }
}
