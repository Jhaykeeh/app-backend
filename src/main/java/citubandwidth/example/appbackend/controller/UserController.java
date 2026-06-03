package citubandwidth.example.appbackend.controller;

//package com.citu.bandwisth.controller;

import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> getProfile() {
        String schoolId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userService.getUserBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserEntity> updateProfile(@RequestBody UserEntity updatedUser) {
        String schoolId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity existing = userService.getUserBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updatedUser.getFirstName() != null) existing.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) existing.setLastName(updatedUser.getLastName());
        if (updatedUser.getEmail() != null) existing.setEmail(updatedUser.getEmail());
        if (updatedUser.getCourse() != null) existing.setCourse(updatedUser.getCourse());
        if (updatedUser.getYearLevel() != null) existing.setYearLevel(updatedUser.getYearLevel());
        if (updatedUser.getContactNumber() != null) existing.setContactNumber(updatedUser.getContactNumber());

        return ResponseEntity.ok(userService.updateUser(existing));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}/disable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> disableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.disableUser(id));
    }

    @PutMapping("/{id}/enable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> enableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.enableUser(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}