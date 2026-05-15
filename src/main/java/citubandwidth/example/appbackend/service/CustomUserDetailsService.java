package citubandwidth.example.appbackend.service;

//package com.citu.bandwisth.security;

import citubandwidth.example.appbackend.entity.UserEntity;
import citubandwidth.example.appbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String schoolId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findBySchoolId(schoolId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with school ID: " + schoolId));

        return new org.springframework.security.core.userdetails.User(
                user.getSchoolId(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
