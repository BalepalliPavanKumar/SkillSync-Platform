package com.skillsync.auth_service.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;

import com.skillsync.auth_service.dto.AuthResponse;
import com.skillsync.auth_service.dto.LoginRequest;
import com.skillsync.auth_service.dto.UserRegisterRequest;
import com.skillsync.auth_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public java.util.Map<String, String> register(@Valid @RequestBody UserRegisterRequest request) {
        System.out.println("DEBUG: Received registration request for: " + request.getEmail());
        String msg = authService.register(request);
        System.out.println("DEBUG: Registration successful for: " + request.getEmail());
        return java.util.Collections.singletonMap("message", msg);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String token = authHeader;
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return authService.refresh(token);
    }
}
