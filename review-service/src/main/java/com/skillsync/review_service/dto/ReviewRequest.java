
package com.skillsync.review_service.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long mentorId;
    private Long userId;
    private int rating;
    private String comment;
}