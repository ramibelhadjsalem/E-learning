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
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id;
    @JsonView(View.base.class)
    private String username ;
    private String email ;
    private String phoneNumber;
    private String password ;


    private String firstName ;
    private String lastName ;

    private  boolean activated ;
    private boolean verified ;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

}
