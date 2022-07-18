package com.example.Elearning.DTOs.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmSms {
    @NotBlank
    private String phoneNumber ;
    @NotBlank
    private String code ;
}
