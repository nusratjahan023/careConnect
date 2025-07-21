package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    private Review review1;
    private Review review2;

    @BeforeEach
    void setUp() {
        review1 = new Review();
        review1.setReviewForId(100L);
        review1.setReviewedById(200L);
        review1.setRating(4);
        review1.setDescription("Great service!");
        review1.setSubmittedOn(new Date());

        review2 = new Review();
        review2.setReviewForId(100L);
        review2.setReviewedById(201L);
        review2.setRating(5);
        review2.setDescription("Excellent!");
        review2.setSubmittedOn(new Date());

        reviewRepository.save(review1);
        reviewRepository.save(review2);
    }

    @AfterEach
    void tearDown() {
        reviewRepository.deleteAll();
    }

    @Test
    void itShouldFindAllByReviewForId() {
        List<Review> reviews = reviewRepository.findAllByReviewForId(100L);

        assertThat(reviews).isNotNull();
        assertThat(reviews.size()).isEqualTo(2);
        assertThat(reviews)
                .extracting(Review::getDescription)
                .containsExactlyInAnyOrder("Great service!", "Excellent!");
    }

    @Test
    void itShouldReturnEmptyWhenNoReviewFoundForUserId() {
        List<Review> reviews = reviewRepository.findAllByReviewForId(999L);

        assertThat(reviews).isEmpty();
    }
}
