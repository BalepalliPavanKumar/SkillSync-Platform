package com.skillsync.review_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillsync.review_service.entity.Review;
import com.skillsync.review_service.repository.ReviewRepository;
import com.skillsync.review_service.exception.ResourceNotFoundException;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReview() {
        Review review = new Review();
        review.setRating(5);
        review.setComment("Great mentor!");

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review savedReview = reviewService.addReview(review);

        assertNotNull(savedReview);
        assertEquals(5, savedReview.getRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testGetAverageRating() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(createReview(4));
        reviews.add(createReview(5));

        when(reviewRepository.findByMentorId(1L)).thenReturn(reviews);

        double avg = reviewService.getAverageRating(1L);

        assertEquals(4.5, avg);
    }

    @Test
    void testGetById_NotFound() {
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reviewService.getById(999L);
        });
    }

    private Review createReview(int rating) {
        Review r = new Review();
        r.setRating(rating);
        return r;
    }
}
