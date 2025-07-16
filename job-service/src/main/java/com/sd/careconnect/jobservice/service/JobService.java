package com.sd.careconnect.jobservice.service;

import com.sd.careconnect.jobservice.entity.JobApplication;
import com.sd.careconnect.jobservice.entity.JobPost;

import java.util.List;
import java.util.Optional;

public interface JobService {
    JobPost createJob(JobPost jobPost);
    List<JobPost> getAllJobs();
    Optional<JobPost> getJobById(Long id);
    JobPost updateJob(Long id, JobPost updatedJob);
    void deleteJob(Long id);
    JobPost assignCaregiver(Long jobId, Long careGiverId);

    JobApplication completeJob(Long jobPostId, Long caregiverId);
}
