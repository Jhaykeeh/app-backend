package citubandwidth.example.appbackend.dto;

//package com.citu.bandwisth.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "School ID is required")
    private String schoolId;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String deviceBrand;
    private String deviceModel;
}