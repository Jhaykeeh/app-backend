package citubandwidth.example.appbackend.controller;

import citubandwidth.example.appbackend.entity.BandwidthUsageEntity;
import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.repository.BandwidthUsageRepository;
import citubandwidth.example.appbackend.repository.UserRepository;
import citubandwidth.example.appbackend.service.BandwidthService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bandwidth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class BandwidthController {

    private final BandwidthService bandwidthService;
    private final UserRepository userRepository;
    private final BandwidthUsageRepository bandwidthUsageRepository;

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String schoolId = auth.getName();
        UserEntity user = userRepository.findBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    @PostMapping("/log")
    public ResponseEntity<BandwidthUsageEntity> logUsage(@RequestBody BandwidthUsageEntity usage) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(bandwidthService.logUsage(userId, usage.getDeviceId(),
                usage.getUsageAmount(), usage.getUnit()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<BandwidthUsageEntity>> getUserUsage() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(bandwidthService.getUserUsage(userId));
    }

    @GetMapping("/user/total")
    public ResponseEntity<Double> getTotalUsage() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(bandwidthService.getTotalUsage(userId));
    }

    @GetMapping("/user/range")
    public ResponseEntity<Double> getUsageInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(bandwidthService.getUsageBetween(userId, start, end));
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BandwidthUsageEntity>> getAllUsage() {
        return ResponseEntity.ok(bandwidthUsageRepository.findAll());
    }
}
