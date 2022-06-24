package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;

public class SectionDto {
    @NotBlank(message = "section Name is Required")
    private String name ;

    public SectionDto() {
    }

    public SectionDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
