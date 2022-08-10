package com.example.Elearning.Models.UserModel;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Models.SectionModels.Section;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.*;

@Entity

@Table(name = "users")


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.base.class)
    private Long id;


    @JsonView(View.base.class)
    private String username ;//this is phoneNumber

    @JsonView(View.extrainfo.class)
    private String email ;

    @JsonIgnore
    private String password ;


    @JsonView(View.extrainfo.class)
    private  String firstname ;
    @JsonView(View.extrainfo.class)
    private  String lastname  ;

    @JsonView(View.extrainfo.class)
    @Column(length = 3000)
    private String bio ;

    @JsonView(View.extrainfo.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob ;
    @JsonView(View.extrainfo.class)
    @Column(length = 3000)
    private String jobTitle ;

    @JsonView(View.extrainfo.class)
    private String lang ;
    @JsonView(View.extrainfo.class)
    private  boolean joinMail ;

    @JsonView(View.extrainfo.class)
    private Boolean[] traininglevel = {true,true,true} ;

    @JsonView(View.extrainfo.class)
    private  String mettingType;

    @JsonView(View.extrainfo.class)
    private String gender ;

    @JsonView(View.extrainfo.class)
    @Column(length = 3000)
    private  String adresse ;
    @JsonView(View.extrainfo.class)
    
    private  String photoUrl ;

    @JsonView(View.extrainfo.class)
    @Column(length = 3000)
    private String coverUrl ;

    private  boolean activated ;

    @JsonView(View.extrainfo.class)
    private  boolean confirmed ;
    private String  SmsCode ;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

   
   

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id",referencedColumnName = "id")

    private Set<Education> educations =new HashSet<>() ;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id",referencedColumnName = "id")

    private Set<Experience> experiences =new HashSet<>() ;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
    
    public User(String username, String email, String password,boolean activated,boolean comfirmed) {
    	this.username = username;
        this.email = email;
        this.password = password;
        this.confirmed = comfirmed;
        this.activated = activated;
    	
    }
    public User(Long id, String username, String email, String password, String firstname, String lastname, String bio, Date dob, String jobTitle, String lang, boolean joinMail, Boolean[] traininglevel, String mettingType, String gender, String adresse, String photoUrl, String coverUrl, boolean activated, boolean confirmed, String smsCode, Set<Role> roles, Level level, Section section, Set<Education> educations, Set<Experience> experiences) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.dob = dob;
        this.jobTitle = jobTitle;
        this.lang = lang;
        this.joinMail = joinMail;
        this.traininglevel = traininglevel;
        this.mettingType = mettingType;
        this.gender = gender;
        this.adresse = adresse;
        this.photoUrl = photoUrl;
        this.coverUrl = coverUrl;
        this.activated = activated;
        this.confirmed = confirmed;
        SmsCode = smsCode;
        this.roles = roles;
        this.level = level;
        this.section = section;
        this.educations = educations;
        this.experiences = experiences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isJoinMail() {
        return joinMail;
    }

    public void setJoinMail(boolean joinMail) {
        this.joinMail = joinMail;
    }

    public Boolean[] getTraininglevel() {
        return traininglevel;
    }

    public void setTraininglevel(Boolean[] traininglevel) {
        this.traininglevel = traininglevel;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getSmsCode() {
        return SmsCode;
    }

    public void setSmsCode(String smsCode) {
        SmsCode = smsCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMettingType() {
        return mettingType;
    }

    public void setMettingType(String mettingType) {
        this.mettingType = mettingType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
