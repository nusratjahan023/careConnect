package com.sd.careConnect.service;

import com.sd.careConnect.Enums.JobApplicationStatus;
import com.sd.careConnect.Enums.JobStatus;
import com.sd.careConnect.Enums.Role;
import com.sd.careConnect.entity.AppUser;
import com.sd.careConnect.entity.JobApplication;
import com.sd.careConnect.entity.JobPost;
import com.sd.careConnect.repository.JobApplicationRepository;
import com.sd.careConnect.repository.JobPostRepository;
import com.sd.careConnect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository appUserRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 JobPostRepository jobPostRepository,
                                 UserRepository appUserRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobPostRepository = jobPostRepository;
        this.appUserRepository = appUserRepository;
    }

    public JobApplication applyToJob(Long jobId, Long caregiverId) {
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!jobPost.getStatus().equals(JobStatus.OPEN)) {
            throw new RuntimeException("Cannot apply to a non-OPEN job");
        }

        AppUser caregiver = appUserRepository.findById(caregiverId)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        if (!caregiver.getRole().equals(Role.CAREGIVER.toString())) {
            throw new RuntimeException("Only caregivers can apply");
        }

        boolean alreadyApplied = jobApplicationRepository
                .findByJobPostIdAndCaregiverId(jobId, caregiverId).isPresent();

        if (alreadyApplied) {
            throw new RuntimeException("Already applied to this job");
        }

        JobApplication application = new JobApplication();
        application.setJobPost(jobPost);
        application.setCaregiver(caregiver);
        application.setApplicationTime(LocalDateTime.now());
        application.setStatus(JobApplicationStatus.APPLIED);

        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsForJob(Long jobId) {
        return jobApplicationRepository.findByJobPostId(jobId);
    }
}

