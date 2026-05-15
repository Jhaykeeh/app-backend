package citubandwidth.example.appbackend.controller;

//package com.citu.bandwisth.controller;

import citubandwidth.example.appbackend.dto.DeviceRequest;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import citubandwidth.example.appbackend.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceEntity> registerDevice(@RequestBody DeviceRequest request) {
        // Get userId from authentication
        Long userId = 1L; // Replace with actual user ID from token
        return ResponseEntity.ok(deviceService.registerDevice(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<DeviceEntity>> getUserDevices() {
        Long userId = 1L; // Replace with actual user ID from token
        return ResponseEntity.ok(deviceService.getUserDevices(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@PathVariable Long id,
                                                      @Valid @RequestBody DeviceRequest request) {
        return ResponseEntity.ok(deviceService.updateDevice(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }
}