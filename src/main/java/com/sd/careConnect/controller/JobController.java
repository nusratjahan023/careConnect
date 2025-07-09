package com.sd.careConnect.controller;

import com.sd.careConnect.entity.JobPost;
import com.sd.careConnect.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobPost> createJob(@RequestBody JobPost jobPost) {
        return ResponseEntity.ok(jobService.createJob(jobPost));
    }

    @GetMapping
    public ResponseEntity<List<JobPost>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPost> updateJob(@PathVariable Long id, @RequestBody JobPost updatedJob) {
        return ResponseEntity.ok(jobService.updateJob(id, updatedJob));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
