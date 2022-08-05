package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ExperienceDto {

    private  Long id;
    @NotBlank(message = "description  is required ")
    private  String description ;
}
