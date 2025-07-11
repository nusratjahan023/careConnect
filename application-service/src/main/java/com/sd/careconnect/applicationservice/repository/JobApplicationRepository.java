package com.sd.careconnect.applicationservice.repository;

import com.sd.careconnect.applicationservice.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobPostId(Long jobPostId);
    List<JobApplication> findByCaregiverId(Long caregiverId);
    JobApplication getJobApplicationById(Long jobApplicationId);
}
