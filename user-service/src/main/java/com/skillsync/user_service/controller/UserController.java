
package com.skillsync.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillsync.user_service.entity.UserProfile;
import com.skillsync.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // POST: Create user profile
    @PostMapping
    public UserProfile create(@RequestBody UserProfile user) {
        return service.createUser(user);
    }

    // GET: All users
    @GetMapping
    public List<UserProfile> getAll() {
        return service.getAllUsers();
    }

    // GET: User by ID
    @GetMapping("/{id}")
    public UserProfile getById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}