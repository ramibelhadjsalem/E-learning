package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpProf {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    @NotBlank(message = "phoneNumber is required")
    private String username;
    @NotBlank
    private String email;

    @NotBlank
    private String password;
    @NotNull
    private Long idLevel;
}

