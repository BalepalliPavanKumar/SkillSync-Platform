package com.skillsync.notification_service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Declares the session_queue so notification-service creates it in RabbitMQ
 * if it doesn't exist yet (durable, so it survives RabbitMQ restarts).
 */
@Configuration
public class RabbitMQConfig {

    public static final String SESSION_QUEUE = "session_queue";

    @Bean
    public Queue sessionQueue() {
        return new Queue(SESSION_QUEUE, true); // durable = true
    }
}
