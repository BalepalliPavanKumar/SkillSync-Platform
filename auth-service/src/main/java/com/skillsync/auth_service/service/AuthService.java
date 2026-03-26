package com.skillsync.auth_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.auth_service.dto.LoginRequest;
import com.skillsync.auth_service.dto.UserRegisterRequest;
import com.skillsync.auth_service.entity.User;
import com.skillsync.auth_service.repository.UserRepository;
import com.skillsync.auth_service.util.JwtUtil;

import com.skillsync.auth_service.exception.BadCredentialsException;
import com.skillsync.auth_service.exception.ResourceNotFoundException;

import com.skillsync.auth_service.dto.AuthResponse;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;

    // ✅ REGISTER USER
    public String register(UserRegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest, User.class);
        if (user.getRole() != null && !user.getRole().startsWith("ROLE_")) {
            user.setRole("ROLE_" + user.getRole());
        }
        userRepository.save(user);
        return "User Registered Successfully";
    }

    // ✅ LOGIN USER
    public AuthResponse login(LoginRequest request) {

        // Fetch user from DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        // Check password
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException("Invalid password provided");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(user, token);
    }

    public AuthResponse refresh(String token) {
        jwtUtil.validateToken(token);
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        String newToken = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(user, newToken);
    }
}
