package com.skillsync.user_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.skillsync.user_service.dto.UserProfileDto;
import com.skillsync.user_service.entity.UserProfile;
import com.skillsync.user_service.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProfile() {
        // Prepare DTO
        UserProfileDto dto = new UserProfileDto();
        dto.setName("testuser");
        dto.setEmail("test@example.com");

        // Prepare Entity
        UserProfile profile = new UserProfile();
        profile.setName("testuser");
        profile.setEmail("test@example.com");

        // Mocking ModelMapper
        when(modelMapper.map(any(), eq(UserProfile.class))).thenReturn(profile);
        when(modelMapper.map(any(), eq(UserProfileDto.class))).thenReturn(dto);
        
        // Mocking Repository
        when(repository.save(any(UserProfile.class))).thenReturn(profile);

        // Execute
        UserProfileDto result = userService.createUser(dto);

        // Verify
        assertNotNull(result);
        assertEquals("testuser", result.getName());
        verify(repository, times(1)).save(any(UserProfile.class));
    }
}
