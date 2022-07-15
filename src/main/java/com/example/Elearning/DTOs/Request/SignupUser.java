package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SignupUser {
    @NotBlank
    private  String phoneNumber;
    @NotBlank
    private  String firstname;
    @NotBlank
    private  String lastname;

    @NotBlank
    private String password ;

    @NotBlank
    private  String lycee;

    @NotNull
    private Long idlevel ;
    @NotNull
    private Long idsection ;

    public SignupUser() {
    }

    public SignupUser(String phoneNumber, String firstname, String lastname, String password, String lycee, Long idlevel, Long idsection) {
        this.phoneNumber = phoneNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.lycee = lycee;
        this.idlevel = idlevel;
        this.idsection = idsection;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLycee() {
        return lycee;
    }

    public void setLycee(String lycee) {
        this.lycee = lycee;
    }

    public Long getIdlevel() {
        return idlevel;
    }

    public void setIdlevel(Long idlevel) {
        this.idlevel = idlevel;
    }

    public Long getIdsection() {
        return idsection;
    }

    public void setIdsection(Long idsection) {
        this.idsection = idsection;
    }
}


