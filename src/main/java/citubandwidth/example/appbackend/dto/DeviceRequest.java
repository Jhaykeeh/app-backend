package citubandwidth.example.appbackend.dto;

//package com.citu.bandwisth.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class DeviceRequest {
    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Model is required")
    private String model;
}