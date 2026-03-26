
package com.skillsync.session_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "session_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
 // ✅ ADD THIS (DON’T CREATE NEW CLASS)
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}