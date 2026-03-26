
package com.skillsync.session_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

import com.skillsync.session_service.entity.Session;
import com.skillsync.session_service.repository.SessionRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.skillsync.session_service.exception.ResourceNotFoundException;
@Service
public class SessionService {

    @Autowired
    private SessionRepository repository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    public Session createSession(Session session) {
        session.setStatus("REQUESTED");

        Session saved = repository.save(session);

        publishEvent(saved, "SESSION_REQUESTED");

        return saved;
    }

    public Session getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
    }

    public Session acceptSession(Long id) {
        Session s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        s.setStatus("ACCEPTED");
        Session saved = repository.save(s);
        publishEvent(saved, "SESSION_ACCEPTED");
        return saved;
    }

    public Session rejectSession(Long id) {
        Session s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        s.setStatus("REJECTED");
        Session saved = repository.save(s);
        publishEvent(saved, "SESSION_REJECTED");
        return saved;
    }

    public Session cancelSession(Long id) {
        Session s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        s.setStatus("CANCELLED");
        Session saved = repository.save(s);
        publishEvent(saved, "SESSION_CANCELLED");
        return saved;
    }

    public Session completeSession(Long id) {
        Session s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        s.setStatus("COMPLETED");
        Session saved = repository.save(s);
        publishEvent(saved, "SESSION_COMPLETED");
        return saved;
    }

    public void sendReminder(Long id) {
        Session s = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        publishEvent(s, "SESSION_REMINDER");
    }

    public List<Session> getByUser(Long userId) {
        return repository.findAll()
                .stream()
                .filter(s -> s.getLearnerId().equals(userId))
                .toList();
    }

    private void publishEvent(Session session, String eventType) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("eventType", eventType);
            payload.put("id", session.getId());
            payload.put("mentorId", session.getMentorId());
            payload.put("learnerId", session.getLearnerId());
            payload.put("sessionDate", session.getSessionDate());
            payload.put("status", session.getStatus());

            String json = objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend("session_queue", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
