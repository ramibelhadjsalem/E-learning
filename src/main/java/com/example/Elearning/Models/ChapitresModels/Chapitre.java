package com.example.Elearning.Models.ChapitresModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.PagesModels.Page;
import com.example.Elearning.Models.Subjects.Subject;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Chapitres")
public class Chapitre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id ;
    @JsonView(View.base.class)
    private String name ;

    @JsonView(View.base.class)
    private Long TotalPages = 0L;

    @ManyToOne
    @JsonView(View.base.class)
    private Subject subject;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonView(View.Internal.class)
    private Set<Page> pages = new HashSet<>() ;

    public Chapitre() {
    }

    public Chapitre(Long id, String name, Long totalPages, Subject subject, Set<Page> pages) {
        this.id = id;
        this.name = name;
        TotalPages = totalPages;
        this.subject = subject;
        this.pages = pages;
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

    public Long getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Long totalPages) {
        TotalPages = totalPages;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }
}
