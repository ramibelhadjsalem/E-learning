package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Services.LevelServices.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/level")
public class LevelContoller {  //Todo:validation
    @Autowired
    LevelService levelService;
    public LevelContoller() {
    }

    @GetMapping()
    public List<Level> getAllLevels() {
        return levelService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> CreateLevels(@RequestBody Level level) {
        if (levelService.existbyName(level.getName()))
            return new ResponseEntity<>(new MessageResponse("level already exists"), HttpStatus.CONFLICT);
        return new ResponseEntity<>(levelService.saveUpadta(level), HttpStatus.CREATED);
    }
    @GetMapping("/{id}") //todo:notfound exeption
    public ResponseEntity<Level> getLevelById(@PathVariable Long id) {
        return new ResponseEntity<>(levelService.getByid(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteLevelById(@PathVariable Long id) {
        levelService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("level deleted "),HttpStatus.OK);
    }
}
