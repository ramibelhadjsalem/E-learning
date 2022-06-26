package com.example.Elearning.Services.LevelServices;

import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Repositorys.LevelRepo.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class LevelService {
    @Autowired
    LevelRepository levelRepository ;

    public LevelService() {
    }

    public List<Level> getAll(){
        return levelRepository.findAll() ;
    }
    public Level saveUpadta(Level level){

        return levelRepository.save(level);
    }
    public Level getByid(Long id){

        try {
        return levelRepository.findById(id).get();
        }catch (Exception ex){
            throw new NotFoundException("Level with id :"+id+" Not found");
        }
    }
    public void deleteById(Long id){
        try {
            levelRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("Level with id :"+id+" Not found");
        }

    }
    public boolean existbyName(String name){
        return levelRepository.existsByName(name);
    }

    public Level findbyName(String name) {
       return levelRepository.findByName(name).get();
    }
}
