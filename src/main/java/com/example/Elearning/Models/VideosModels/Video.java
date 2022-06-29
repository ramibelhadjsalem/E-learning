package com.example.Elearning.Models.VideosModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Models.UserModel.User;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id ;
    @JsonView(View.base.class)
    private String description ;
    @JsonView(View.base.class)
    private String videoUrl ;
    @JsonView(View.base.class)
    private Date dateOfCreation ;
    @JsonView(View.base.class)
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="page_id", nullable=false)
    private Page page ;
    @JsonView(View.base.class)
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="user_id", nullable=false)
    private User user ;

    public Video() {
        this.dateOfCreation = new Date();
    }

    public Video(Long id, String description, String videoUrl, Page page, User user) {
        this();
        this.id = id;
        this.description = description;
        this.videoUrl = videoUrl;
        this.page = page;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
