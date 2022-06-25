package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.SectionDto;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.SectionModels.Section;
import com.example.Elearning.Services.SubjectServices.SectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/section")
public class SectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping
    public List<Section> getAll(){
        return sectionService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Section> findByid(@PathVariable Long id){
        return new ResponseEntity<>(sectionService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addSection(@Valid @RequestBody SectionDto sectionDto){

        Section section = modelMapper.map(sectionDto,Section.class);
        if(sectionService.existsByName(sectionDto.getName())){
            return new ResponseEntity<>(new MessageResponse(sectionDto.getName()+" already exists"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(sectionService.save(section),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        sectionService.deleteByid(id);
        return new ResponseEntity<>(new MessageResponse("section deleted"),HttpStatus.OK);
    }


}
