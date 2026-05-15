package citubandwidth.example.appbackend.controller;

//package com.citu.bandwisth.controller;

import citubandwidth.example.appbackend.entity.BandwidthUsageEntity;
import citubandwidth.example.appbackend.service.BandwidthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bandwidth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BandwidthController {

    private final BandwidthService bandwidthService;

    @PostMapping("/log")
    public ResponseEntity<BandwidthUsageEntity> logUsage(@RequestBody BandwidthUsageEntity usage) {
        // Get userId and deviceId from authentication
        Long userId = 1L;
        Long deviceId = 1L;
        return ResponseEntity.ok(bandwidthService.logUsage(userId, deviceId,
                usage.getUsageAmount(), usage.getUnit()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<BandwidthUsageEntity>> getUserUsage() {
        Long userId = 1L; // Replace with actual user ID from token
        return ResponseEntity.ok(bandwidthService.getUserUsage(userId));
    }

    @GetMapping("/user/total")
    public ResponseEntity<Double> getTotalUsage() {
        Long userId = 1L; // Replace with actual user ID from token
        return ResponseEntity.ok(bandwidthService.getTotalUsage(userId));
    }

    @GetMapping("/user/range")
    public ResponseEntity<Double> getUsageInRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        Long userId = 1L; // Replace with actual user ID from token
        return ResponseEntity.ok(bandwidthService.getUsageBetween(userId, start, end));
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<BandwidthUsageEntity>> getAllUsage() {
        // Admin endpoint - return all usage
        return ResponseEntity.ok(bandwidthService.getUserUsage(1L)); // Replace with logic for all
    }
}
