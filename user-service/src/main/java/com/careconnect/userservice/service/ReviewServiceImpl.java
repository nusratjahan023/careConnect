package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.ReviewDto;
import com.careconnect.userservice.entity.Review;
import com.careconnect.userservice.repository.ReviewRepository;
import com.careconnect.userservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private ReviewDto convertToDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getReviewForId(),
                review.getReviewedById(),
                review.getDescription(),
                review.getSubmittedOn()
        );
    }

    private Review convertToEntity(ReviewDto dto) {
        return new Review(
                dto.getReviewForId(),
                dto.getReviewedById(),
                dto.getDescription(),
                dto.getSubmittedOn()
        );
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return convertToDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setReviewForId(reviewDto.getReviewForId());
            review.setReviewedById(reviewDto.getReviewedById());
            review.setDescription(reviewDto.getDescription());
            review.setSubmittedOn(reviewDto.getSubmittedOn());
            Review updated = reviewRepository.save(review);
            return convertToDto(updated);
        }
        return null; // or throw exception
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
