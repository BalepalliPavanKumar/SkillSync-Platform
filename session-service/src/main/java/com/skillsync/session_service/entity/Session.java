
package com.skillsync.session_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Mentor ID is required")
    private Long mentorId;

    @NotNull(message = "Learner ID is required")
    private Long learnerId;

    @NotBlank(message = "Session date is required")
    private String sessionDate;

    @NotBlank(message = "Status is required")
    private String status;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getMentorId() { return mentorId; }
    public void setMentorId(Long mentorId) { this.mentorId = mentorId; }

    public Long getLearnerId() { return learnerId; }
    public void setLearnerId(Long learnerId) { this.learnerId = learnerId; }

    public String getSessionDate() { return sessionDate; }
    public void setSessionDate(String sessionDate) { this.sessionDate = sessionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}