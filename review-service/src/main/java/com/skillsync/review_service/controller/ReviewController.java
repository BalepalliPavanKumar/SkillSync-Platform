
package com.skillsync.review_service.controller;

import com.skillsync.review_service.dto.ReviewRequest;
import com.skillsync.review_service.entity.Review;
import com.skillsync.review_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public Review addReview(@RequestBody ReviewRequest request) {
        Review review = Review.builder()
                .mentorId(request.getMentorId())
                .userId(request.getUserId())
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        return service.addReview(review);
    }

    @GetMapping("/{id}")
    public Review getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/mentor/{mentorId}")
    public List<Review> getReviews(@PathVariable Long mentorId) {
        return service.getReviewsByMentor(mentorId);
    }

    @GetMapping("/mentor/{mentorId}/average")
    public double getAverageRating(@PathVariable Long mentorId) {
        return service.getAverageRating(mentorId);
    }
}