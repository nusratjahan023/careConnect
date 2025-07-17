package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.JobExperienceDto;
import com.careconnect.userservice.entity.JobExperience;
import com.careconnect.userservice.repository.JobExperienceRepository;
import com.careconnect.userservice.service.JobExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobExperienceServiceImpl implements JobExperienceService {

    @Autowired
    private JobExperienceRepository repository;

    @Override
    public JobExperienceDto save(JobExperienceDto dto) {
        JobExperience experience = new JobExperience(
                dto.getInstitution(),
                dto.getDegree(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getDesignation(),
                dto.getDescription()
        );
        experience = repository.save(experience);
        dto.setId(experience.getId());
        return dto;
    }

    @Override
    public JobExperienceDto update(Long id, JobExperienceDto dto) {
        JobExperience experience = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job experience not found"));
        experience.setInstitution(dto.getInstitution());
        experience.setDegree(dto.getDegree());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setDesignation(dto.getDesignation());
        experience.setDescription(dto.getDescription());

        repository.save(experience);
        dto.setId(experience.getId());
        return dto;
    }

    @Override
    public List<JobExperienceDto> getAll() {
        return repository.findAll().stream().map(exp -> {
            JobExperienceDto dto = new JobExperienceDto();
            dto.setId(exp.getId());
            dto.setInstitution(exp.getInstitution());
            dto.setDegree(exp.getDegree());
            dto.setStartDate(exp.getStartDate());
            dto.setEndDate(exp.getEndDate());
            dto.setDesignation(exp.getDesignation());
            dto.setDescription(exp.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
