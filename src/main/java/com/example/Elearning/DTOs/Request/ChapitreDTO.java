package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ChapitreDTO {
    @NotBlank(message = "name is Required")

    private String name;
    @NotBlank(message = "SujectName is Required")
    private String subjectName;

    public ChapitreDTO() {
    }

    public ChapitreDTO(String name, String subjectName) {
        this.name = name;
        this.subjectName = subjectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
