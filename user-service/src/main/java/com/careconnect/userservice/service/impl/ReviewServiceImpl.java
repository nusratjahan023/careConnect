package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.dto.ReviewDto;
import com.careconnect.userservice.entity.Review;
import com.careconnect.userservice.repository.ReviewRepository;
import com.careconnect.userservice.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    private ReviewDto convertToDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getReviewForId(),
                review.getReviewedById(),
                review.getDescription(),
                review.getSubmittedOn(),
                review.getRating()
        );
    }

    private Review convertToEntity(ReviewDto dto) {
        return new Review(
                dto.getReviewForId(),
                dto.getReviewedById(),
                dto.getDescription(),
                dto.getSubmittedOn(),
                dto.getRating()
        );
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        AppUser user = userRepository.findById(reviewDto.getReviewForId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        int oldTotalReview = user.getTotalReviews() == null ? 0: user.getTotalReviews();

        int newTotalReviews = oldTotalReview + 1;
        float newRating = (user.getRating() * oldTotalReview + reviewDto.getRating()) / newTotalReviews;
        user.setRating(newRating);
        user.setTotalReviews(newTotalReviews);

        userRepository.save(user);

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
            review.setRating(reviewDto.getRating());
            Review updated = reviewRepository.save(review);
            return convertToDto(updated);
        }
        return null;
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

    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findAllByReviewForId(userId);
    }

}
