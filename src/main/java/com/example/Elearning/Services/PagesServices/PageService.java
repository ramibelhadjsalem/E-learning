package com.example.Elearning.Services.PagesServices;

import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Repositorys.PagesModels.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PageService {
    @Autowired
    PageRepository pageRepository ;
    public PageService() {

    }
    public List<Page> findAll(){
        return pageRepository.findAll();
    }
    public Page findById(Long id){
        try {
            return pageRepository.findById(id).get();
        }catch (Exception ex){
            throw new NoSuchElementException("page with id : "+id+" Not found");
        }

    }
    public Page findByName(String name){
        try {
            return pageRepository.findByName(name).get();
        }catch (Exception ex){
            throw new NoSuchElementException("page with name : "+name+" Not found");
        }

    }
    public Page Save(Page page){
        return pageRepository.save(page);
    }
    public void deleteByid(Long id){
        try {
            pageRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("page with id : "+id+" is not found");
        }
    }
}
