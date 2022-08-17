package com.example.Elearning.Controllers;
import com.example.Elearning.DTOs.Request.InfoEleveUpdate;
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
@RequestMapping("/api/usereleve")
public class UserEleveController {
    @Autowired private UserService userService ;
    @Autowired private TokenUtils tokenUtils;
    @Autowired private ModelMapper mapper ;
    @Autowired private StorageService storageService;
    @Autowired private ImgService imgSer;
    @Autowired private PasswordEncoder encoder;

    public UserEleveController() {
    }

    @GetMapping
    @JsonView(View.extrainfo.class)
    public ResponseEntity<User> getcurrentUser(){
        return new ResponseEntity<>(userService.findById(tokenUtils.ExtractId()), HttpStatus.OK);
    }
    @PostMapping
    @JsonView(View.extrainfo.class)
    public ResponseEntity<?> updateUserinfos(@RequestBody @NotNull InfoEleveUpdate infoUpdate){

        try{

            User user  = userService.findById(tokenUtils.ExtractId());
            if(infoUpdate.getUsername()!=null) user.setUsername(infoUpdate.getUsername());
            if(infoUpdate.getFirstname()!=null) user.setFirstname(infoUpdate.getFirstname());
            if(infoUpdate.getLastname()!=null) user.setLastname(infoUpdate.getLastname());
            if(infoUpdate.getDob()!=null) user.setDob(infoUpdate.getDob());
            if(infoUpdate.getAdresse()!=null) user.setAdresse(infoUpdate.getAdresse());
            if(infoUpdate.getLevel()!=null) user.setLevel(infoUpdate.getLevel());

            if(infoUpdate.getCoverUrl()!=null) {
                if(user.getCoverUrl()!=infoUpdate.getCoverUrl()) user.setCoverUrl(infoUpdate.getCoverUrl());

            }
            if(infoUpdate.getPhotoUrl()!=null) {
                if( user.getPhotoUrl()!=infoUpdate.getPhotoUrl()) user.setPhotoUrl(infoUpdate.getPhotoUrl());
            }


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
