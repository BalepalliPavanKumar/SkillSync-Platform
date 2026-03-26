
package com.skillsync.session_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.skillsync.session_service.entity.Session;
import com.skillsync.session_service.service.SessionService;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService service;

    @PostMapping
    public Session create(@Valid @RequestBody Session session) {
        return service.createSession(session);
    }

    @PutMapping("/{id}/accept")
    public Session accept(@PathVariable Long id) {
        return service.acceptSession(id);
    }

    @PutMapping("/{id}/reject")
    public Session reject(@PathVariable Long id) {
        return service.rejectSession(id);
    }

    @PutMapping("/{id}/cancel")
    public Session cancel(@PathVariable Long id) {
        return service.cancelSession(id);
    }

    @PutMapping("/{id}/complete")
    public Session complete(@PathVariable Long id) {
        return service.completeSession(id);
    }

    @PutMapping("/{id}/remind")
    public java.util.Map<String, String> remind(@PathVariable Long id) {
        service.sendReminder(id);
        return java.util.Collections.singletonMap("message", "Session reminder sent");
    }

    @GetMapping("/{id}")
    public Session getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Session> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}
