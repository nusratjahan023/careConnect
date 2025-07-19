package com.sd.careconnect.jobservice.repository;

import com.sd.careconnect.jobservice.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByClientId(Long id);
}
