package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.SubjectDTO;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Models.SectionModels.Section;
import com.example.Elearning.Models.Subjects.Subject;
import com.example.Elearning.Services.LevelServices.LevelService;
import com.example.Elearning.Services.SubjectServices.SectionService;
import com.example.Elearning.Services.SubjectServices.SubjectService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/subject")

public class SubjectController {
    Logger logger = LoggerFactory.getLogger(SubjectController.class);
    @Autowired
    SubjectService subjectService;
    @Autowired
    LevelService levelService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SectionService sectionService ;

    public SubjectController() {
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectByid(@PathVariable Long id){
        Subject subject = subjectService.findById(id);
        return new ResponseEntity<>(subject,HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addSubject(@Valid @RequestBody SubjectDTO subjectDTO){
        Subject subject=modelMapper.map(subjectDTO,Subject.class);
        Level level = levelService.findbyName(subjectDTO.getLevelname());

        Set<Section> sections = new HashSet<>();
        for(String sectionName : subjectDTO.getSections()){
            Section sectiontoAdd = sectionService.findByName(sectionName);
            sections.add(sectiontoAdd);
        }

        subject.setSections(sections);
        subject.setLevel(level);
        Subject subject1=subjectService.save(subject);
        level.getSubjects().add(subject);
        levelService.saveUpadta(level);




        return new ResponseEntity<>(subject1,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteByid(@PathVariable Long id){
        subjectService.deleteByid(id);
        return new ResponseEntity<>(new MessageResponse("subject deleted "),HttpStatus.OK);
    }

}
