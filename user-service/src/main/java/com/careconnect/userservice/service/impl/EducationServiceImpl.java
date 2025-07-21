package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.dto.EducationDto;
import com.careconnect.userservice.entity.Education;
import com.careconnect.userservice.repository.EducationRepository;
import com.careconnect.userservice.repository.UserRepository;
import com.careconnect.userservice.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public EducationDto saveEducation(EducationDto dto) {
        AppUser user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("UserDetails not found"));

        Education education = mapToEntity(dto);
        education.setUserDetails(user.getUserDetails());

        return mapToDto(educationRepository.save(education));
    }

    @Override
    public EducationDto updateEducation(Long id, EducationDto dto) {
        Optional<Education> optional = educationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Education not found with ID: " + id);
        }

        Education existing = optional.get();
        existing.setInstitution(dto.getInstitution());
        existing.setDegree(dto.getDegree());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setScore(dto.getScore());
        existing.setDescription(dto.getDescription());

        return mapToDto(educationRepository.save(existing));
    }

    @Override
    public boolean deleteEducation(Long id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public EducationDto getEducationById(Long id) {
        return educationRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Education not found with ID: " + id));
    }

    @Override
    public List<EducationDto> getAllEducations() {
        return educationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private Education mapToEntity(EducationDto dto) {
        return new Education(
                dto.getInstitution(),
                dto.getDegree(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getScore(),
                dto.getDescription()
        );
    }

    private EducationDto mapToDto(Education education) {
        return new EducationDto(
                education.getId(),
                education.getInstitution(),
                education.getDegree(),
                education.getStartDate(),
                education.getEndDate(),
                education.getScore(),
                education.getDescription()
        );
    }
}
