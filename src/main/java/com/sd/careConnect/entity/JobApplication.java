package com.sd.careConnect.entity;

import com.sd.careConnect.Enums.JobApplicationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private JobPost jobPost;

    @ManyToOne
    private AppUser caregiver;

    private LocalDateTime applicationTime;

    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public AppUser getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(AppUser caregiver) {
        this.caregiver = caregiver;
    }

    public LocalDateTime getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(LocalDateTime applicationTime) {
        this.applicationTime = applicationTime;
    }

    public JobApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(JobApplicationStatus status) {
        this.status = status;
    }
}
