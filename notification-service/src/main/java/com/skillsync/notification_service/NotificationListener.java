
package com.skillsync.notification_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NotificationListener {
	
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "session_queue")
    public void receiveMessage(String message) {
        System.out.println("📩 Received Message from RabbitMQ: " + message);
        try {
            JsonNode sessionData = objectMapper.readTree(message);
            String sessionId = sessionData.has("id") ? sessionData.get("id").asText() : "UNKNOWN";
            String mentorId = sessionData.has("mentorId") ? sessionData.get("mentorId").asText() : "";
            String learnerId = sessionData.has("learnerId") ? sessionData.get("learnerId").asText() : "";
            String status = sessionData.has("status") ? sessionData.get("status").asText() : "";
            String eventType = sessionData.has("eventType") ? sessionData.get("eventType").asText() : "";

            String notificationTitle;
            if ("MENTOR_APPROVED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Mentor Approved";
            } else if ("SESSION_REMINDER".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Reminder";
            } else if ("SESSION_ACCEPTED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Accepted";
            } else if ("SESSION_REJECTED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Rejected";
            } else if ("SESSION_CANCELLED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Cancelled";
            } else if ("SESSION_COMPLETED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Completed";
            } else if ("SESSION_REQUESTED".equalsIgnoreCase(eventType)) {
                notificationTitle = "Session Requested";
            } else {
                notificationTitle = "Session Status Update";
                if (status != null && !status.isBlank()) {
                    notificationTitle = "Session " + status;
                }
            }
            
            System.out.println("=========================================");
            System.out.println("📧 SENDING EMAIL NOTIFICATION");
            System.out.println("To: Learner " + learnerId + " and Mentor " + mentorId);
            System.out.println("Subject: " + notificationTitle + " (Session " + sessionId + ")");
            System.out.println("=========================================");
            
            System.out.println("📱 SENDING PUSH NOTIFICATION");
            System.out.println("Push Info: " + notificationTitle + " for Session " + sessionId);
            System.out.println("=========================================");
            
        } catch (Exception e) {
            System.err.println("Failed to process notification message: " + e.getMessage());
        }
    }
}
