package com.skillsync.user_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.user_service.dto.UserProfileDto;
import com.skillsync.user_service.entity.UserProfile;
import com.skillsync.user_service.repository.UserRepository;

import com.skillsync.user_service.exception.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    // Create User Profile
    public UserProfileDto createUser(UserProfileDto userDto) {
        UserProfile user = modelMapper.map(userDto, UserProfile.class);
        UserProfile savedUser = repository.save(user);
        return modelMapper.map(savedUser, UserProfileDto.class);
    }

    // Get All Users
    public List<UserProfileDto> getAllUsers() {
        return repository.findAll().stream()
                .map(user -> modelMapper.map(user, UserProfileDto.class))
                .collect(Collectors.toList());
    }

    // Get User By ID
    public UserProfileDto getUserById(Long id) {
        UserProfile user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserProfileDto.class);
    }

    // Update User Profile
    public UserProfileDto updateUser(Long id, UserProfileDto userDto) {
        UserProfile user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        user.setSkills(userDto.getSkills());
        user.setProfileImageUrl(userDto.getProfileImageUrl());

        UserProfile savedUser = repository.save(user);
        return modelMapper.map(savedUser, UserProfileDto.class);
    }
}
