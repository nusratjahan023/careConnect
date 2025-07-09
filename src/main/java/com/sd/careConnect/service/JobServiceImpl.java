package com.sd.careConnect.service;

import com.sd.careConnect.Enums.JobStatus;
import com.sd.careConnect.entity.JobPost;
import com.sd.careConnect.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobPostRepository jobRepository;

    public JobServiceImpl(JobPostRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public JobPost createJob(JobPost jobPost) {
        return jobRepository.save(jobPost);
    }

    @Override
    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
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
            existing.setStatus(updatedJob.getStatus());
            existing.setAssignedTo(updatedJob.getAssignedTo());
            return jobRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
