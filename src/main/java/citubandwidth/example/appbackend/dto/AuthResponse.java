package citubandwidth.example.appbackend.dto;

//package com.citu.bandwisth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String schoolId;
    private String email;
    private String role;
    private Long userId;
    private String firstName;
    private String lastName;
}