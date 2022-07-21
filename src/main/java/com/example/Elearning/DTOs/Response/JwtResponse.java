package com.example.Elearning.DTOs.Response;

import java.util.List;

public class JwtResponse {
  private String token;
  private  String refresh_token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String firstName ;
  private  String lastName ;
  private List<String> roles;

  public JwtResponse(String accessToken,String refresh_token, Long id, String firstName,
                     String lastName,String username, String email, List<String> roles) {

    this.id = id;
    this.username = username;
    this.email = email;
    this.firstName=firstName;
    this.lastName=lastName;

    this.roles = roles;
    this.token = accessToken;
    this.refresh_token = refresh_token;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
