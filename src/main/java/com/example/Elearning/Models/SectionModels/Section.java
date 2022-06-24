package com.example.Elearning.Models.SectionModels;

import javax.persistence.*;


@Entity
@Table(name = "sections",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        })
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;

    public Section() {
    }

    public Section(Long id, String name) {
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
}
