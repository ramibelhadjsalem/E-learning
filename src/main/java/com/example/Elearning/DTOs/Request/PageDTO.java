package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PageDTO {
    @NotBlank(message = "name page is required")
    @Size(min = 3,message = "min length is 3 character")
    private String name ;
    @NotNull(message = "chapitre id  is required")
    private Long chapitre_id;
    public PageDTO() {
    }

    public PageDTO(String name, Long chapitre_id) {
        this.name = name;

        this.chapitre_id = chapitre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getChapitre_id() {
        return chapitre_id;
    }

    public void setChapitre_id(Long chapitre_id) {
        this.chapitre_id = chapitre_id;
    }
}
