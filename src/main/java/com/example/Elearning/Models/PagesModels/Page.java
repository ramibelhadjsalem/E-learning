package com.example.Elearning.Models.PagesModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.ChapitresModels.Chapitre;
import com.example.Elearning.Models.FilesModules.File;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id;

    @JsonView(View.base.class)
    private String name ;

    @JsonView(View.base.class)
    private Number pageNumber ;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="chapitre_id")
    @JsonView(View.page.class)
    private Chapitre chapitre;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Collection<File> videos = new ArrayList<>();

    public Page() {
    }

    public Page(Long id, String name, Number pageNumber, Chapitre chapitre) {
        this.id = id;
        this.name = name;
        this.pageNumber = pageNumber;
        this.chapitre = chapitre;
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

    public Number getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Number pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }
}
