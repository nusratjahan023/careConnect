package com.careconnect.userservice.service;

import com.careconnect.userservice.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto saveReview(ReviewDto reviewDto);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    void deleteReview(Long id);
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getAllReviews();
}
