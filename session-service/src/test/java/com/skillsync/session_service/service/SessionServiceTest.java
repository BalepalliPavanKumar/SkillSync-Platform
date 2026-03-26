package com.skillsync.session_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillsync.session_service.entity.Session;
import com.skillsync.session_service.repository.SessionRepository;
import com.skillsync.session_service.exception.ResourceNotFoundException;

class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSession_Success() throws Exception {
        Session session = new Session();
        session.setLearnerId(1L);
        session.setMentorId(101L);

        when(sessionRepository.save(any(Session.class))).thenReturn(session);
        when(objectMapper.writeValueAsString(any())).thenReturn("{}");

        Session createdSession = sessionService.createSession(session);

        assertNotNull(createdSession);
        assertEquals("REQUESTED", createdSession.getStatus());
        verify(sessionRepository, times(1)).save(any(Session.class));
        verify(rabbitTemplate, times(1)).convertAndSend(eq("session_queue"), anyString());
    }

    @Test
    void testGetById_NotFound() {
        when(sessionRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            sessionService.getById(999L);
        });
    }

    @Test
    void testAcceptSession() {
        Session session = new Session();
        session.setId(1L);
        session.setStatus("REQUESTED");

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(any(Session.class))).thenReturn(session);

        Session updatedSession = sessionService.acceptSession(1L);

        assertEquals("ACCEPTED", updatedSession.getStatus());
        verify(sessionRepository, times(1)).save(any(Session.class));
    }
}
