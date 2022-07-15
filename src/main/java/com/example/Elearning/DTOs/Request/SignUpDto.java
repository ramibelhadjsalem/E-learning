package com.example.Elearning.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    @NotBlank(message = "phoneNumber is required")
    private String username ;

    @NotBlank
    private String password ;
    @NotNull
    private  Long idlevel;

    @NotNull
    private  Long idsection;

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
