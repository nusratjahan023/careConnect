package com.sd.careconnect.jobservice.service;

import com.sd.careconnect.jobservice.Enums.JobApplicationStatus;
import com.sd.careconnect.jobservice.Enums.JobStatus;
import com.sd.careconnect.jobservice.entity.JobApplication;
import com.sd.careconnect.jobservice.entity.JobPost;
import com.sd.careconnect.jobservice.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobPostRepository jobRepository;

    private final  JobApplicationService jobApplicationService;

    public JobServiceImpl(JobPostRepository jobRepository, JobApplicationService jobApplicationService) {
        this.jobRepository = jobRepository;
        this.jobApplicationService = jobApplicationService;
    }

    @Override
    public JobPost createJob(JobPost jobPost) {
        jobPost.setStatus(JobStatus.OPEN);
        return jobRepository.save(jobPost);
    }

    @Override
    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<JobPost> getJobsByClientId(Long id) {
        return jobRepository.findByClientId(id);
    }

    @Override
    public Optional<JobPost> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public JobPost updateJob(Long id, JobPost updatedJob) {
        return jobRepository.findById(id).map(existing -> {
            existing.setTitle(updatedJob.getTitle());
            existing.setDescription(updatedJob.getDescription());
            existing.setStartTime(updatedJob.getStartTime());
            existing.setEndTime(updatedJob.getEndTime());
            existing.setLocation(updatedJob.getLocation());
            existing.setAssignedUserId(updatedJob.getAssignedUserId());
            return jobRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public JobPost assignCaregiver(Long jobId, Long careGiverId) {
        jobRepository.findById(jobId).map(existing -> {
            existing.setAssignedUserId(careGiverId);
            existing.setStatus(JobStatus.ASSIGNED);

            jobApplicationService.updateStatus(jobApplicationService.getJobApplicationByJobPostIdAndCaregiverId(jobId, careGiverId), JobApplicationStatus.APPROVED);

            return jobRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Unable to assign Caregiver, Job not found"));
        return null;
    }

    public JobApplication completeJob(Long jobPostId, Long caregiverId) {
        jobRepository.findById(jobPostId).map(existing -> {
            existing.setAssignedUserId(caregiverId);
            existing.setStatus(JobStatus.COMPLETED);

            return jobRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Unable to complete job, Job not found"));
        return null;
    }
}
