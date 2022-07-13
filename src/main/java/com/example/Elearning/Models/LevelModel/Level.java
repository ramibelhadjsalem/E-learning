package com.example.Elearning.Models.LevelModel;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.SectionModels.Section;
import com.example.Elearning.Models.Subjects.Subject;
import com.example.Elearning.Models.UserModel.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;
    private  String name ;
    @OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE )
    private Set<Subject> subjects = new HashSet<>();

    @JsonView(View.base.class)
    @ManyToMany
    private Set<Section> sections = new HashSet<>();

    public Level() {
    }

    public Level(Long id, String name) {
        this.id = id;
        this.name = name;

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
