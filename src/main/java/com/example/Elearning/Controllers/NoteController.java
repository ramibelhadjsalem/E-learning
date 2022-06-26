package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.NoteDto;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.NoteModels.Note;
import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Services.NotesServices.NoteService;
import com.example.Elearning.Services.PagesServices.PageService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Utils.TokenUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("api/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
     ModelMapper modelMapper ;
    @Autowired
    UserService userService;
    @Autowired
    PageService pageService;
    @Autowired
    TokenUtils tokenUtils;


    public NoteController() {
    }

    @GetMapping
    @PreAuthorize(" hasRole('ADMIN')")
    @JsonView(View.note.class)
    public List<Note> findAll(){
        return noteService.findAll();
    }

    @GetMapping("/ouwn")
    @PreAuthorize(" hasRole('ADMIN')")
    @JsonView(View.note.class)
    public List<Note> findAllByUser(){
        return noteService.findAllByuser(tokenUtils.ExtractId());
    }

    @PostMapping
    @JsonView(View.note.class)
    public ResponseEntity<?>  save(@Valid @RequestBody NoteDto noteDto , @RequestParam("file" )MultipartFile file){
        Long idUser = tokenUtils.ExtractId();
        Page page = pageService.findById(noteDto.getId_page());


        Note note =new Note(null,noteDto.getMyNote(),"http://localhost:8080/api/note",page, tokenUtils.ExtractId());



        return new ResponseEntity<>( noteService.Save(note), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.note.class)
    public ResponseEntity<Note> findByid(@PathVariable Long id){
        return new ResponseEntity<>(noteService.findbyId(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteByid(@PathVariable Long id){

        Note note = noteService.findbyId(id);
        if(note.getUser_id()!= tokenUtils.ExtractId()){
            return new ResponseEntity<>(new MessageResponse(" not allawed to delete this note"),HttpStatus.FORBIDDEN );
        }
        noteService.DeleteById(id);
        return ResponseEntity.ok(new MessageResponse("Note deleted"));
    }
}
