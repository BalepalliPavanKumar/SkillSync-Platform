
package com.skillsync.review_service.service;

import com.skillsync.review_service.entity.Review;
import com.skillsync.review_service.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.skillsync.review_service.exception.ResourceNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public Review addReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return repository.save(review);
    }

    public Review getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByMentor(Long mentorId) {
        return repository.findByMentorId(mentorId);
    }

    public double getAverageRating(Long mentorId) {
        List<Review> reviews = repository.findByMentorId(mentorId);

        if (reviews.isEmpty()) return 0.0;

        double sum = reviews.stream()
                .mapToInt(Review::getRating)
                .sum();

        return sum / reviews.size();
    }
}