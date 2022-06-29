package com.example.Elearning.Models.NoteModels;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.PagesModels.Page;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id;
    @JsonView(View.base.class)
    private String mynote;
    @JsonView(View.base.class)
    private String VocalUri;

    @JsonView(View.base.class)
    private Long user_id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "page_Id")
    @JsonView(View.base.class)
    private Page page;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Note() {
    }

    public Note(Long id, String mynote, String vocalUri, Page page, Long user_id) {
        this.id = id;
        this.mynote = mynote;
        VocalUri = vocalUri;
        this.page = page;
        this.user_id = user_id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMynote() {
        return mynote;
    }

    public void setMynote(String mynote) {
        this.mynote = mynote;
    }

    public String getVocalUri() {
        return VocalUri;
    }

    public void setVocalUri(String vocalUri) {
        VocalUri = vocalUri;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
