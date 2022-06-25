package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PageDTO {
    @NotBlank(message = "name page is required")
    @Size(min = 3,message = "min length is 3 character")
    private String name ;

    @NotNull(message = "page number is required")
    @Min(value = 1,message = "page number have to over 0")
    private Number pageNumber ;

    public PageDTO(String name, Number pageNumber) {
        this.name = name;
        this.pageNumber = pageNumber;
    }

    public PageDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Number pageNumber) {
        this.pageNumber = pageNumber;
    }
}
