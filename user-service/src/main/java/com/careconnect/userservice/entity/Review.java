package com.careconnect.userservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reviewForId;
    private Long reviewedById;
    private String description;
    private Date submittedOn;

    public Review(Long reviewForId, Long reviewedById, String description, Date submittedOn) {
        this.reviewForId = reviewForId;
        this.reviewedById = reviewedById;
        this.description = description;
        this.submittedOn = submittedOn;
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
}
