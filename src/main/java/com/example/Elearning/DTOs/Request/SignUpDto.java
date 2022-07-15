package com.example.Elearning.DTOs.Request;

import com.example.Elearning.Models.LevelModel.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    @NotBlank(message = "phoneNumber is required")
    private String username ;

    @NotBlank
    private String password ;
    @NotNull
    private  Long idLevel ;

    @NotNull
    private  Long idSection ;





}
