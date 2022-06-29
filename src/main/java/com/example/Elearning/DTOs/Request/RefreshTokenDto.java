package com.example.Elearning.DTOs.Request;

import javax.validation.constraints.NotBlank;

public class RefreshTokenDto {
    @NotBlank(message = "refresh token is required")
    private String refreshToken ;

    public RefreshTokenDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenDto() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
