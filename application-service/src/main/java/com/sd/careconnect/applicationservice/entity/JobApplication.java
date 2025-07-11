package com.sd.careconnect.applicationservice.entity;

import com.sd.careconnect.applicationservice.Enums.JobApplicationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobPostId;
    private Long caregiverId;
    private LocalDateTime applicationTime;

    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status;

    // Constructors
    public JobApplication() {}

    public JobApplication(Long jobPostId, Long caregiverId, LocalDateTime applicationTime, JobApplicationStatus status) {
        this.jobPostId = jobPostId;
        this.caregiverId = caregiverId;
        this.applicationTime = applicationTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
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
