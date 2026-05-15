package citubandwidth.example.appbackend.dto;

//package com.citu.bandwisth.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class AuthRequest {
    @NotBlank(message = "School ID is required")
    private String schoolId;

    @NotBlank(message = "Password is required")
    private String password;
}
