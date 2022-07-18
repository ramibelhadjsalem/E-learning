package com.example.Elearning.Security.Twilio;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SmsRequest {

    @NotNull
    private final String phoneNumber ;
    @NotBlank
    private  final String message ;

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
