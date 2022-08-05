package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.EducationReq;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Services.Userservices.EducationService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Utils.TokenUtils;
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
@RequestMapping("api/education")

public class EducationController {
    @Autowired private EducationService educationService;
    @Autowired private ModelMapper mapper;
    @Autowired private TokenUtils tokenUtils;
    @Autowired private  UserService userService ;
    public EducationController() {
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
    @GetMapping
    @JsonView(View.base.class)
    public List<Education> findAllOuwns(){
        return educationService.findallouwns(tokenUtils.ExtractId());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
    @PostMapping
    @JsonView(View.base.class)
    public ResponseEntity<Education> Save(@RequestBody @Valid EducationReq education){
        Education education1 = mapper.map(education ,Education.class);

        User user = userService.findById(tokenUtils.ExtractId());
        education1.setUser(user);
        Education education2 = educationService.AddEducation(education1);
        user.getEducations().add(education1);

       /* userService.saveUser(user);*/
        return new ResponseEntity<>(education2, HttpStatus.CREATED) ;

    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable Long id){
        Education education  = educationService.findById(id);
        if(!education.getUser().getId().equals(tokenUtils.ExtractId())) return new ResponseEntity<>(new MessageResponse("not Allowed to delete this"),HttpStatus.FORBIDDEN);


        educationService.deleteById(id);

        return  new ResponseEntity<>(new MessageResponse("deleted"),HttpStatus.OK);
    }
}
