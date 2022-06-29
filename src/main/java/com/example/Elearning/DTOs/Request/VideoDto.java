package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VideoDto {
    @NotNull(message = "Page id is required")
    private Long page_id;

    @NotBlank(message = "video description is required")
    private String description  ;

    public VideoDto(Long page_id,  String description) {
        this.page_id = page_id;

        this.description = description;
    }

    public VideoDto() {
    }

    public Long getPage_id() {
        return page_id;
    }

    public void setPage_id(Long page_id) {
        this.page_id = page_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
