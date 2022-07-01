package com.example.Elearning.Controllers;


import com.example.Elearning.DTOs.Request.EventDto;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.EventsModules.Event;
import com.example.Elearning.Security.serviceUser.jwt.JwtUtils;
import com.example.Elearning.Services.EventsServices.EventService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Utils.TokenUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/event")
public class EventControlller {
    @Autowired private EventService eventService;
    @Autowired private UserService userService;
    @Autowired private ModelMapper mapper;
    @Autowired private TokenUtils tokenUtils;

    public EventControlller() {
    }

    @GetMapping
    @JsonView(View.base.class)
    public List<Event> findAll(){
        return eventService.findAll();
    }
    @PostMapping
    @JsonView(View.base.class)
    public ResponseEntity<?> AddEvents(@Valid @RequestBody EventDto eventDto){
        Event event  =mapper.map(eventDto ,Event.class);
        event.setUser(userService.findById(tokenUtils.ExtractId()));

        return new ResponseEntity<>(eventService.Save(event), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.base.class)
    public ResponseEntity<Event> findByid(@PathVariable Long id){
        return ResponseEntity.ok(eventService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(eventService.findById(id).getUser().getId()!=tokenUtils.ExtractId()){
            return new ResponseEntity<>(new MessageResponse("not allowed to delete this event"),HttpStatus.UNAUTHORIZED);
        }

        eventService.deleteByid(id);
        return ResponseEntity.ok(new MessageResponse("event deleted !"));
    }@DeleteMapping("admin/{id}")
    public ResponseEntity deleteForAdminById(@PathVariable Long id){
        eventService.deleteByid(id);
        return ResponseEntity.ok(new MessageResponse("event deleted !"));
    }
}
