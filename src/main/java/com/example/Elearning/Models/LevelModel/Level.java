package com.example.Elearning.Models.LevelModel;

import com.example.Elearning.Models.Subjects.Subject;

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
    @OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.ALL )
    private Set<Subject> subjects = new HashSet<>();


    public Level(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level() {
    }
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
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
}
