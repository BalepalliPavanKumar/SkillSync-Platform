package com.skillsync.group_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GroupMessageRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Message content is required")
    private String content;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
