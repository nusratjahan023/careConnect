package com.sd.careconnect.jobservice.service;

import com.sd.careconnect.jobservice.entity.JobApplication;
import com.sd.careconnect.jobservice.Enums.JobApplicationStatus;
import com.sd.careconnect.jobservice.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public JobApplication applyToJob(Long jobPostId, Long caregiverId) {
        JobApplication application = new JobApplication(
                jobPostId,
                caregiverId,
                LocalDateTime.now(),
                JobApplicationStatus.PENDING
        );
        return repository.save(application);
    }

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public List<JobApplication> getApplicationsByJobPostId(Long jobPostId) {
        return repository.findByJobPostId(jobPostId);
    }

    public List<JobApplication> getApplicationsByCaregiverId(Long caregiverId) {
        return repository.findByCaregiverId(caregiverId);
    }

    public JobApplication getJobApplicationByJobPostIdAndCaregiverId(Long jobPostId, Long caregiverId) {
        return repository.findByJobPostIdAndCaregiverId(jobPostId, caregiverId);
    }

    public Optional<JobApplication> updateStatus(Long id, JobApplicationStatus newStatus) {
        Optional<JobApplication> optional = repository.findById(id);
        optional.ifPresent(app -> {
            app.setStatus(newStatus);
            repository.save(app);
        });
        return optional;
    }

    public Optional<JobApplication> getJobApplicationById(Long id) {
        return repository.findById(id);
    }

    public boolean deleteApplication(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
