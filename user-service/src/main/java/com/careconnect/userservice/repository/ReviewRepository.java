package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByReviewForId(Long userId);
}
