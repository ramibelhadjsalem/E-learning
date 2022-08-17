package com.example.Elearning.DTOs.Request;

import com.example.Elearning.Models.LevelModel.Level;

import java.util.Date;

public class InfoEleveUpdate {

    private String username ;

    private String password ;

    private  String firstname ;

    private  String lastname  ;

    private Date dob ;

    private  String adresse ;

    private  String photoUrl ;

    private String coverUrl ;

    private Level level;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public InfoEleveUpdate(String username, String password, String firstname, String lastname, Date dob, String adresse, String photoUrl, String coverUrl, Level level) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.adresse = adresse;
        this.photoUrl = photoUrl;
        this.coverUrl = coverUrl;
        this.level = level;
    }
}
