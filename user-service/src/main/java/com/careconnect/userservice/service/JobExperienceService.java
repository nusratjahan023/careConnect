package com.careconnect.userservice.service;

import com.careconnect.userservice.entity.JobExperienceDto;

import java.util.List;

public interface JobExperienceService {
    JobExperienceDto save(JobExperienceDto jobExperienceDto);
    JobExperienceDto update(Long id, JobExperienceDto jobExperienceDto);
    List<JobExperienceDto> getAll();
    void delete(Long id);
}
