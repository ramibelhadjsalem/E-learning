package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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



}
