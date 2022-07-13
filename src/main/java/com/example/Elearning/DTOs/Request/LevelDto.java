package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;

public class LevelDto {

    @NotBlank(message = "name page is required")
    private  String name ;
    @NotEmpty(message = "sections in level is required")
    private Collection<Long> sections = new ArrayList<>();

    public LevelDto(String name, Collection<Long> sections) {
        this.name = name;
        this.sections = sections;
    }

    public LevelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Long> getSections() {
        return sections;
    }

    public void setSections(Collection<Long> sections) {
        this.sections = sections;
    }
}
