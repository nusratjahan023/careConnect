package com.sd.careconnect.jobservice.controller;

import com.sd.careconnect.jobservice.entity.JobApplication;
import com.sd.careconnect.jobservice.Enums.JobApplicationStatus;
import com.sd.careconnect.jobservice.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyToJob(@RequestParam Long jobPostId,
                                                     @RequestParam Long caregiverId) {
        JobApplication application = service.applyToJob(jobPostId, caregiverId);
        return ResponseEntity.ok(application);
    }

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return service.getAllApplications();
    }

    @GetMapping("/by-job/{jobPostId}")
    public List<JobApplication> getByJobPost(@PathVariable Long jobPostId) {
        return service.getApplicationsByJobPostId(jobPostId);
    }

    @GetMapping("/by-caregiver/{caregiverId}")
    public List<JobApplication> getByCaregiver(@PathVariable Long caregiverId) {
        return service.getApplicationsByCaregiverId(caregiverId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(@PathVariable Long id,
                                                       @RequestParam JobApplicationStatus status) {
        Optional<JobApplication> updated = service.updateStatus(id, status);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        boolean deleted = service.deleteApplication(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}