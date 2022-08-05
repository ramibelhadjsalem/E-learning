package com.example.Elearning.Models.UserModel;

import com.example.Elearning.DTOs.Views.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private  Long id ;

    @JsonView(View.base.class)
    private  String description  ;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    public Experience(Long id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Experience() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
