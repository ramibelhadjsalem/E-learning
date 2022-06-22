package com.example.Elearning.DTOs.Request;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotBlank;
public class LoginForm {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

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

    public LoginForm() {
        this.username="";
        this.password="";
    }
}
