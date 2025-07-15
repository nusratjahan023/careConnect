package com.sd.careconnect.jobservice.controller;

import com.sd.careconnect.jobservice.Enums.JobApplicationStatus;
import com.sd.careconnect.jobservice.entity.JobApplication;
import com.sd.careconnect.jobservice.entity.JobPost;
import com.sd.careconnect.jobservice.entity.JobPostDTO;
import com.sd.careconnect.jobservice.service.JobApplicationService;
import com.sd.careconnect.jobservice.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private  final JobApplicationService jobApplicationService;

    public JobController(JobService jobService, JobApplicationService jobApplicationService) {
        this.jobService = jobService;
        this.jobApplicationService = jobApplicationService;
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
    public ResponseEntity<JobPostDTO> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(jobPost -> {
                    List<JobApplication> applications = jobApplicationService.getApplicationsByJobPostId(id);
                    JobPostDTO dto = new JobPostDTO("Job found", true, false, null);
                    // Copy jobPost fields into dto
                    dto.setId(jobPost.getId());
                    dto.setTitle(jobPost.getTitle());
                    dto.setLocation(jobPost.getLocation());
                    dto.setDescription(jobPost.getDescription());
                    dto.setRequirements(jobPost.getRequirements());
                    dto.setStartTime(jobPost.getStartTime());
                    dto.setEndTime(jobPost.getEndTime());
                    dto.setHourlyRate(jobPost.getHourlyRate());
                    dto.setClientId(jobPost.getClientId());
                    dto.setApplications(applications);
                    // Add additional logic for flags
                    dto.setCanApply(true); // or custom logic
                    dto.setCanEdit(true); // or custom logic
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    JobPostDTO errorDto = new JobPostDTO("Job not found", false, true, "No job post with the given ID.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
                });
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

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyToJob(@RequestParam Long jobPostId,
                                                     @RequestParam Long caregiverId) {
        JobApplication application = jobApplicationService.applyToJob(jobPostId, caregiverId);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/applications")
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @GetMapping("/applications/by-job/{jobPostId}")
    public List<JobApplication> getByJobPost(@PathVariable Long jobPostId) {
        return jobApplicationService.getApplicationsByJobPostId(jobPostId);
    }

    @GetMapping("/applications/by-caregiver/{caregiverId}")
    public List<JobApplication> getByCaregiver(@PathVariable Long caregiverId) {
        return jobApplicationService.getApplicationsByCaregiverId(caregiverId);
    }

    @PutMapping("/applications/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(@PathVariable Long id,
                                                       @RequestParam JobApplicationStatus status) {
        Optional<JobApplication> updated = jobApplicationService.updateStatus(id, status);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        boolean deleted = jobApplicationService.deleteApplication(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/accept-caregiver")
    public ResponseEntity<?> acceptCaregiver(@RequestBody Map<String, Long> data) {
        Long jobId = data.get("jobId");
        Long careGiverId = data.get("careGiverId");

        JobPost jobPost = jobService.assignCaregiver(jobId, careGiverId);

        return ResponseEntity.ok(jobPost);
    }
}
