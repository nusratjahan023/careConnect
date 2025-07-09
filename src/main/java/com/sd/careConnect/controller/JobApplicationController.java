package com.sd.careConnect.controller;

import com.sd.careConnect.entity.JobApplication;
import com.sd.careConnect.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyToJob(@RequestParam Long jobId, @RequestParam Long caregiverId) {
        JobApplication application = jobApplicationService.applyToJob(jobId, caregiverId);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplications(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobApplicationService.getApplicationsForJob(jobId));
    }
}

