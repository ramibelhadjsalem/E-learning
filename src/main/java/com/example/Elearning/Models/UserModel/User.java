package com.example.Elearning.Models.UserModel;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Models.SectionModels.Section;

import com.example.Elearning.Models.UserModel.Role;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id;


    @JsonView(View.base.class)
    private String username ;//this is phoneNumber
    private String email ;
    
    private String password ;



    private  String firstname ;
    private  String lastname  ;


    private  boolean activated ;
    private  boolean confirmed ;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

   
   

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secion_id", referencedColumnName = "id")
    private Section section;

    public User() {

    }

    public User(Long id, String username, String email, String password, String firstname, String lastname, boolean activated, boolean confirmed, Set<Role> roles, Level level, Section section) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.activated = activated;
        this.confirmed = confirmed;
        this.roles = roles;
        this.level = level;
        this.section = section;
    }
}
