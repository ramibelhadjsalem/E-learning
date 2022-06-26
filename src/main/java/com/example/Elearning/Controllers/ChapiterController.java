package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.ChapitreDTO;

import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.ChapitresModels.Chapitre;
import com.example.Elearning.Models.Subjects.Subject;
import com.example.Elearning.Services.ChapitresServices.ChapitreService;
import com.example.Elearning.Services.SubjectServices.SubjectService;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chapitre")

public class ChapiterController {
    @Autowired
    ChapitreService chapitreService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @JsonView(View.base.class)
    public List<Chapitre> findAll(){
        return chapitreService.findAll();
    }
    @GetMapping("/{id}")
    @JsonView(View.base.class)
    public ResponseEntity<?> findById(@PathVariable Long id){

        return new ResponseEntity<>(chapitreService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    @JsonView(View.base.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addChapitre(@Valid @RequestBody ChapitreDTO chapitreDTO){
        Chapitre chapitre=modelMapper.map(chapitreDTO,Chapitre.class);
        Subject subject = subjectService.findByName(chapitreDTO.getSubjectName());
        chapitre.setSubject(subject);
        return new ResponseEntity<>(chapitreService.Save(chapitre),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> DeleteById(@PathVariable Long id){


        chapitreService.deleteById(id);

        return new ResponseEntity<>(new MessageResponse("chapitre deleted "),HttpStatus.OK);
    }
}
