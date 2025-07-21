package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.dto.EducationDto;
import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.entity.Education;
import com.careconnect.userservice.entity.UserDetails;
import com.careconnect.userservice.repository.EducationRepository;
import com.careconnect.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EducationServiceImplTest {

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EducationServiceImpl educationService;

    private AutoCloseable closeable;

    private AppUser user;
    private EducationDto educationDto;
    private Education education;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        UserDetails userDetails = new UserDetails();
        userDetails.setId(1L);

        user = new AppUser();
        user.setId(1L);
        user.setUserDetails(userDetails);

        educationDto = new EducationDto(null, "Harvard", "BSc Computer Science",
                new Date(),
                new Date(),
                3.8F, "Studied AI and software engineering");

        education = new Education("Harvard", "BSc Computer Science",
                new Date(),
                new Date(),
                3.8F, "Studied AI and software engineering");
        education.setId(1L);
        education.setUserDetails(user.getUserDetails());
    }

    @Test
    void saveEducation_shouldSaveAndReturnDto() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(educationRepository.save(any(Education.class))).thenReturn(education);

        educationDto.setUserId(1L);
        EducationDto savedDto = educationService.saveEducation(educationDto);

        assertNotNull(savedDto);
        assertEquals("Harvard", savedDto.getInstitution());
        verify(educationRepository).save(any(Education.class));
    }

    @Test
    void updateEducation_shouldUpdateAndReturnDto() {
        Education updated = new Education("MIT", "MSc AI", new Date(),
                new Date(), 4.0F, "Deep Learning");
        updated.setId(1L);

        when(educationRepository.findById(1L)).thenReturn(Optional.of(education));
        when(educationRepository.save(any(Education.class))).thenReturn(updated);

        EducationDto updateDto = new EducationDto(null, "MIT", "MSc AI",
                new Date(), new Date(), 4.0F, "Deep Learning");

        EducationDto result = educationService.updateEducation(1L, updateDto);

        assertEquals("MIT", result.getInstitution());
        assertEquals("MSc AI", result.getDegree());
    }

    @Test
    void deleteEducation_shouldDeleteIfExists() {
        when(educationRepository.existsById(1L)).thenReturn(true);

        boolean result = educationService.deleteEducation(1L);

        assertTrue(result);
        verify(educationRepository).deleteById(1L);
    }

    @Test
    void deleteEducation_shouldReturnFalseIfNotExists() {
        when(educationRepository.existsById(2L)).thenReturn(false);

        boolean result = educationService.deleteEducation(2L);

        assertFalse(result);
        verify(educationRepository, never()).deleteById(2L);
    }

    @Test
    void getEducationById_shouldReturnDto() {
        when(educationRepository.findById(1L)).thenReturn(Optional.of(education));

        EducationDto result = educationService.getEducationById(1L);

        assertEquals("Harvard", result.getInstitution());
    }

    @Test
    void getEducationById_shouldThrowIfNotFound() {
        when(educationRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> educationService.getEducationById(99L));

        assertTrue(exception.getMessage().contains("Education not found"));
    }

    @Test
    void getAllEducations_shouldReturnAllDtos() {
        List<Education> list = List.of(education);
        when(educationRepository.findAll()).thenReturn(list);

        List<EducationDto> result = educationService.getAllEducations();

        assertEquals(1, result.size());
        assertEquals("Harvard", result.get(0).getInstitution());
    }
}
