package com.sd.careconnect.jobservice.repository;

import com.sd.careconnect.jobservice.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobPostId(Long jobPostId);
    List<JobApplication> findByCaregiverId(Long caregiverId);
    JobApplication findByJobPostIdAndCaregiverId(Long jobPostId, Long caregiverId);
    JobApplication getJobApplicationById(Long jobApplicationId);
}
