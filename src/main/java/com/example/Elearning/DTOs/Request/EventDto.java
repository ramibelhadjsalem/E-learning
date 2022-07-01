package com.example.Elearning.DTOs.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EventDto {
    @NotBlank(message = "name of the event is required ")
    private String name ;
    @NotBlank(message = "description of the event is required ")
    private String description;
    private Float price=0F;

    private Date DateOfStart = new Date();

    private Long maxUser;
    public EventDto() {
    }

    public EventDto(String name, String description, Float price, Date dateOfStart, Long maxUser) {
        this.name = name;
        this.description = description;
        this.price = price;
        DateOfStart = dateOfStart;
        this.maxUser = maxUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDateOfStart() {
        return DateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        DateOfStart = dateOfStart;
    }

    public Long getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Long maxUser) {
        this.maxUser = maxUser;
    }
}
