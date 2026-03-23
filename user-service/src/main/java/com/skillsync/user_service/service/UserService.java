
package com.skillsync.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.user_service.entity.UserProfile;
import com.skillsync.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Create User Profile
    public UserProfile createUser(UserProfile user) {
        return repository.save(user);
    }

    // Get All Users
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    // Get User By ID
    public UserProfile getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}