package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.PageDTO;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Services.PagesServices.PageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/page")
public class PageController {
    @Autowired
    PageService pageService;
    @Autowired
    ModelMapper modelMapper;

    public PageController() {
    }

    @GetMapping
    public List<Page> findAll(){
        return pageService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return  ResponseEntity.ok(pageService.findById(id));
    }
    @GetMapping("/pagename/{name}")
    public ResponseEntity<?> findById(@PathVariable String name){
        return  ResponseEntity.ok(pageService.findByName(name));
    }
    @PostMapping
    public ResponseEntity<?> SavePage(@Valid @RequestBody PageDTO pageDTO){
        Page page= modelMapper.map(pageDTO ,Page.class);
        return new ResponseEntity<>(pageService.Save(page), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        pageService.deleteByid(id);
        return new ResponseEntity<>(new MessageResponse("Page Deleted"),HttpStatus.OK);
    }

}
