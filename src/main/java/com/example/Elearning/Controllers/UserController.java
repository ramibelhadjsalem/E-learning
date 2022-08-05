package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.InfoUpdate;
import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.UserModel.Img;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Services.Userservices.ImgService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Storage.StorageService;
import com.example.Elearning.Utils.TokenUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired private UserService userService ;
    @Autowired private TokenUtils tokenUtils;
    @Autowired private ModelMapper mapper ;
    @Autowired private StorageService storageService;
    @Autowired private ImgService imgSer;
    @Autowired private PasswordEncoder encoder;
    public UserController() {
    }

    @GetMapping
    @JsonView(View.extrainfo.class)
    public ResponseEntity<User> getcurrentUser(){
        return new ResponseEntity<>(userService.findById(tokenUtils.ExtractId()), HttpStatus.OK);
    }
    @PostMapping
    @JsonView(View.extrainfo.class)
    public ResponseEntity<?> updateUserinfos(@RequestBody @NotNull InfoUpdate infoUpdate){

        try{

            User user  = userService.findById(tokenUtils.ExtractId());
            if(infoUpdate.getUsername()!=null) user.setUsername(infoUpdate.getUsername());
            if(infoUpdate.getEmail()!=null) user.setEmail(infoUpdate.getEmail());
            if(infoUpdate.getFirstname()!=null) user.setFirstname(infoUpdate.getFirstname());
            if(infoUpdate.getLastname()!=null) user.setLastname(infoUpdate.getLastname());

            if(infoUpdate.getBio()!=null) user.setBio(infoUpdate.getBio());

            if(infoUpdate.getDob()!=null) user.setDob(infoUpdate.getDob());
            if(infoUpdate.getJobTitle()!=null) user.setJobTitle(infoUpdate.getJobTitle());
            if(infoUpdate.getLang()!=null) user.setLang(infoUpdate.getLang());

            user.setJoinMail(infoUpdate.isJoinMail());
            if(infoUpdate.getTraininglevel()!=null) user.setTraininglevel(infoUpdate.getTraininglevel());
            if(infoUpdate.getMettingType()!=null) user.setMettingType(infoUpdate.getMettingType());

            if(infoUpdate.getGender()!=null) user.setGender(infoUpdate.getGender());
            if(infoUpdate.getAdresse()!=null) user.setAdresse(user.getAdresse());


            if(infoUpdate.getPassword()!=null ) user.setPassword(encoder.encode(infoUpdate.getPassword()));

            return new ResponseEntity<>(userService.saveUser(user),HttpStatus.ACCEPTED);

        }catch (Error error){
            throw new RuntimeException(error.getMessage());
        }


    }
    @PostMapping("/img")
    @JsonView(View.base.class)
    public ResponseEntity<Img> addImg(@RequestParam("file") MultipartFile file){

        if(file.isEmpty() ) return ResponseEntity.badRequest().build();

        Img img = new Img(
                storageService.store(file,"img")
        );
        img.setUser(userService.findById(tokenUtils.ExtractId()));
        return new ResponseEntity<>(imgSer.AddImg(img),HttpStatus.CREATED);
    }

}
