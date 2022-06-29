package com.example.Elearning.DTOs.Response;

public class JwtRefreshResponse {
    private String accessToken ;

    public JwtRefreshResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
