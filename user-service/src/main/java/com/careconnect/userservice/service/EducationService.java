package com.careconnect.userservice.service;

import com.careconnect.userservice.dto.EducationDto;

import java.util.List;

public interface EducationService {
    EducationDto saveEducation(EducationDto dto);
    EducationDto updateEducation(Long id, EducationDto dto);
    boolean deleteEducation(Long id);
    EducationDto getEducationById(Long id);
    List<EducationDto> getAllEducations();
}
