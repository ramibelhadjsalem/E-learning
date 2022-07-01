package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.NoteModels.Note;
import com.example.Elearning.Services.NotesServices.NoteService;
import com.example.Elearning.Services.PagesServices.PageService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Storage.StorageService;
import com.example.Elearning.Utils.TokenUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired private StorageService storageService;


    public NoteController() {
    }

    @GetMapping
    @JsonView(View.base.class)
    public List<Note> findAll(){
        return noteService.findAll(tokenUtils.ExtractId());
    }

    @GetMapping("/page/{id}")
    @JsonView(View.base.class)

    public List<Note> findByPage(@PathVariable Long id_page){

        return noteService.findbyPage(id_page,tokenUtils.ExtractId());
    }

    @PostMapping
    @JsonView(View.base.class)
    public ResponseEntity<?>  save(@RequestParam("page_id") Long page_id,
            @RequestParam("mynote") String myNote,
            @RequestParam("file" )MultipartFile file){

        Note note =new Note(null,myNote,null,pageService.findById(page_id), tokenUtils.ExtractId());
        if(!file.isEmpty()){
            note.setVocalUri(storageService.store(file,"note" ));
        }
        return new ResponseEntity<>( noteService.Save(note), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.base.class)
    public ResponseEntity<Note> findByid(@PathVariable Long id){ //todo: verife this
        return new ResponseEntity<>(noteService.findbyId(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteByid(@PathVariable Long id){

        Note note = noteService.findbyId(id);
        if(note.getUser_id()!= tokenUtils.ExtractId()){
            return new ResponseEntity<>(new MessageResponse(" not allawed to delete this note"),HttpStatus.FORBIDDEN );
        }
        noteService.DeleteById(id);
        return ResponseEntity.ok(new MessageResponse("Note deleted"));
    }
}
