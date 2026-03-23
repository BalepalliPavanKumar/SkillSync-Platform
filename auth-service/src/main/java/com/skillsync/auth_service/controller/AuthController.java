
package com.skillsync.auth_service.controller;

import org.springframework.web.bind.annotation.*;

import com.skillsync.auth_service.dto.AuthResponse;
import com.skillsync.auth_service.dto.LoginRequest;
import com.skillsync.auth_service.entity.User;
import com.skillsync.auth_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public java.util.Map<String, String> register(@RequestBody User user) {
        System.out.println("DEBUG: Received registration request for: " + user.getEmail());
        String msg = authService.register(user);
        System.out.println("DEBUG: Registration successful for: " + user.getEmail());
        return java.util.Collections.singletonMap("message", msg);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }
}