package citubandwidth.example.appbackend.controller;

//package com.citu.bandwisth.controller;

import citubandwidth.example.appbackend.dto.DeviceRequest;
import citubandwidth.example.appbackend.entity.DeviceEntity;
import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.repository.UserRepository;
import citubandwidth.example.appbackend.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final UserRepository userRepository;

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String schoolId = auth.getName();
        UserEntity user = userRepository.findBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    @PostMapping
    public ResponseEntity<DeviceEntity> registerDevice(@RequestBody DeviceRequest request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(deviceService.registerDevice(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<DeviceEntity>> getUserDevices() {
        Long userId = getCurrentUserId();
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @PutMapping("/admin/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeviceEntity> approveDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.approveDevice(id));
    }

    @PutMapping("/admin/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeviceEntity> rejectDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.rejectDevice(id));
    }
}