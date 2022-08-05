package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.EducationReq;
import com.example.Elearning.DTOs.Request.ExperienceDto;
import com.example.Elearning.DTOs.Response.EductionAndExperience;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Models.UserModel.Experience;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Services.Userservices.EducationService;
import com.example.Elearning.Services.Userservices.ExperienceService;
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
@RequestMapping("api/experience")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROF')")
public class ExperienceController {

    @Autowired private ExperienceService experienceService;
    @Autowired private EducationService educationService;
    @Autowired private ModelMapper mapper;
    @Autowired private TokenUtils tokenUtils;
    @Autowired private UserService userService ;
    public ExperienceController() {
    }

    @GetMapping
    @JsonView(View.base.class)
    public List<Experience> findAllOuwns(){
        return experienceService.findAllOuwns(tokenUtils.ExtractId());
    }
    @PostMapping
    @JsonView(View.base.class)
    public ResponseEntity<Experience> Save(@RequestBody @Valid ExperienceDto experienceDto){
        Experience experience = mapper.map(experienceDto , Experience.class);

        User user = userService.findById(tokenUtils.ExtractId());
        experience.setUser(user);

        Experience experience1 = experienceService.AddExperience(experience);
        user.getExperiences().add(experience1);

        return new ResponseEntity<>(experience1, HttpStatus.CREATED) ;

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable Long id){
        Experience experience = experienceService.findById(id);

        if(!experience.getUser().getId().equals(tokenUtils.ExtractId())) return new ResponseEntity<>(new MessageResponse("not Allowed to delete this"),HttpStatus.FORBIDDEN);

        experienceService.deleteById(id);
        return  new ResponseEntity<>(new MessageResponse("deleted"),HttpStatus.OK);
    }
    @GetMapping("/experienceandeduction")
    @JsonView(View.base.class)
    public  ResponseEntity<?> getAll(){
        EductionAndExperience ex = new EductionAndExperience();
        Long userid = tokenUtils.ExtractId();
        ex.setExperiences(experienceService.findAllOuwns(userid));
        ex.setEducations(educationService.findallouwns(userid));

        return new ResponseEntity<>(ex,HttpStatus.OK);
    }
}
