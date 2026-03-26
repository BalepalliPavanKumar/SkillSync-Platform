package com.skillsync.auth_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.skillsync.auth_service.dto.UserRegisterRequest;
import com.skillsync.auth_service.entity.User;
import com.skillsync.auth_service.repository.UserRepository;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setRole("USER");

        User user = new User();
        user.setName("testuser");
        
        when(modelMapper.map(any(UserRegisterRequest.class), any())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        String result = authService.register(request);

        assertEquals("User Registered Successfully", result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
