package com.example.Elearning.Models.ChapitresModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.Subjects.Subject;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "Chapitres")
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id ;
    @JsonView(View.base.class)
    private String name ;

    @ManyToOne
    @JsonView(View.base.class)
    private Subject subject;

    public Chapitre(Long id, String name, Subject subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public Chapitre() {
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
