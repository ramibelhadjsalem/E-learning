package com.example.Elearning.Models.Subjects;

import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Models.SectionModels.Section;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL )
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<Section> sections=new HashSet<>();

    public Subject() {}

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public Subject(Long id, String name, Level level, Set<Section> sections) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.sections = sections;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


}
