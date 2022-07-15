package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpProf {
    @NotBlank
    private  String phoneNumber;
    @NotBlank
    private String email;
    @NotBlank
    private  String firstname;
    @NotBlank
    private  String lastname;

    @NotBlank
    private String password ;

    @NotBlank
    private  String lycee;

    @NotNull
    private Long idlevel ;


}
