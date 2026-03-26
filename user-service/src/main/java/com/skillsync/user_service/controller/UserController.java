package com.skillsync.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.skillsync.user_service.dto.UserProfileDto;
import com.skillsync.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // POST: Create user profile
    @PostMapping
    public UserProfileDto create(@Valid @RequestBody UserProfileDto user) {
        return service.createUser(user);
    }

    // GET: All users
    @GetMapping
    public List<UserProfileDto> getAll() {
        return service.getAllUsers();
    }

    // GET: User by ID
    @GetMapping("/{id}")
    public UserProfileDto getById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // PUT: Update user profile
    @PutMapping("/{id}")
    public UserProfileDto update(@PathVariable Long id, @Valid @RequestBody UserProfileDto user) {
        return service.updateUser(id, user);
    }
}
