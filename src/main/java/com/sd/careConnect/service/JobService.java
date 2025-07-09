package com.sd.careConnect.service;

import com.sd.careConnect.entity.JobPost;

import java.util.List;
import java.util.Optional;

public interface JobService {
    JobPost createJob(JobPost jobPost);
    List<JobPost> getAllJobs();
    Optional<JobPost> getJobById(Long id);
    JobPost updateJob(Long id, JobPost updatedJob);
    void deleteJob(Long id);
}
