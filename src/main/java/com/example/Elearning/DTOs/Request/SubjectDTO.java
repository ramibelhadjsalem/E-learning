package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;

public class SubjectDTO {
    @NotBlank(message = "name is required")
    private String name ;
    @NotBlank(message = "lavel name is required")
    private String levelname;

    @NotEmpty(message = "sections is Required")
    private Collection<String> sections =new ArrayList<>();

    public SubjectDTO(String name, String levelname) {
        this.name = name;
        this.levelname = levelname;
    }

    public SubjectDTO() {
    }

    public SubjectDTO(String name, String levelname, Collection<String> sections) {
        this.name = name;
        this.levelname = levelname;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public Collection<String> getSections() {
        return sections;
    }

    public void setSections(Collection<String> sections) {
        this.sections = sections;
    }
}
