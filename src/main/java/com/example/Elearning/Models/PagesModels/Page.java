package com.example.Elearning.Models.PagesModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.ChapitresModels.Chapitre;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
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
    public Page() {
    }
    @ManyToOne
    private Chapitre chapitre;



}
