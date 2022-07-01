package com.example.Elearning.Models.EventsModules;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.UserModel.User;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id ;
    @JsonView(View.base.class)
    private String name ;
    @JsonView(View.base.class)
    private String description;
    @JsonView(View.base.class)
    private Float price;
    @JsonView(View.base.class)
    private Date dateOfStart =new Date(new Date().getTime() + 86400000);
    @JsonView(View.base.class)
    private String videoUrl;
    @JsonView(View.base.class)
    private Long maxUser;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "eventSource_id")
    @JsonView(View.base.class)
    private User user;

    public Event() {
    }

    public Event(Long id, String name, String description, Float price, Date dateOfStart, String videoUrl, Long maxUser, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfStart = dateOfStart;
        this.videoUrl = videoUrl;
        this.maxUser = maxUser;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Long maxUser) {
        this.maxUser = maxUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
