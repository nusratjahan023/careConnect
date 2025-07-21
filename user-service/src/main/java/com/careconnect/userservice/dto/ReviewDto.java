package com.careconnect.userservice.dto;

import java.util.Date;

public class ReviewDto {
    private Long id;
    private Long reviewForId;
    private Long reviewedById;
    private String description;
    private Date submittedOn;
    private float rating;

    public ReviewDto() {
    }

    public ReviewDto(Long id, Long reviewForId, Long reviewedById, String description, Date submittedOn, float rating) {
        this.id = id;
        this.reviewForId = reviewForId;
        this.reviewedById = reviewedById;
        this.description = description;
        this.submittedOn = submittedOn;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewForId() {
        return reviewForId;
    }

    public void setReviewForId(Long reviewForId) {
        this.reviewForId = reviewForId;
    }

    public Long getReviewedById() {
        return reviewedById;
    }

    public void setReviewedById(Long reviewedById) {
        this.reviewedById = reviewedById;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
