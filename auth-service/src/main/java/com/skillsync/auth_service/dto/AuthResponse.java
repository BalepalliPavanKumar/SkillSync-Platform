package com.skillsync.auth_service.dto;

import com.skillsync.auth_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private User user;
    private String token;
}