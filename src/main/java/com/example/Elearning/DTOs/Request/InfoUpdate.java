package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


public class InfoUpdate {

    private String username ;

    private String email ;

    private String password ;

    private  String firstname ;

    private  String lastname  ;


    private String bio ;
    private String jobTitle ;
    private Date dob ;

    private String lang ;

    private  boolean joinMail ;


    private Boolean[] traininglevel ;
    private  String mettingType;

    private String gender ;


    private  String adresse ;

    private  String photoUrl ;

    private String coverUrl ;


	public InfoUpdate(String username, String email, String password, String firstname, String lastname, String bio,
			String jobTitle, Date dob, String lang, boolean joinMail, Boolean[] traininglevel, String mettingType,
			String gender, String adresse, String photoUrl, String coverUrl) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.bio = bio;
		this.jobTitle = jobTitle;
		this.dob = dob;
		this.lang = lang;
		this.joinMail = joinMail;
		this.traininglevel = traininglevel;
		this.mettingType = mettingType;
		this.gender = gender;
		this.adresse = adresse;
		this.photoUrl = photoUrl;
		this.coverUrl = coverUrl;
	}

	public InfoUpdate() {
		
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

	public String getMettingType() {
		return mettingType;
	}

	public void setMettingType(String mettingType) {
		this.mettingType = mettingType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	



}
