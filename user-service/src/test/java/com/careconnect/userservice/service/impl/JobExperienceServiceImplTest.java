package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.dto.JobExperienceDto;
import com.careconnect.userservice.entity.JobExperience;
import com.careconnect.userservice.repository.JobExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobExperienceServiceImplTest {

    @InjectMocks
    private JobExperienceServiceImpl jobExperienceService;

    @Mock
    private JobExperienceRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private JobExperienceDto createDto() {
        JobExperienceDto dto = new JobExperienceDto();
        dto.setInstitution("Company A");
        dto.setDegree("Software Engineering");
        dto.setStartDate(new Date());
        dto.setEndDate(new Date());
        dto.setDesignation("Developer");
        dto.setDescription("Worked on backend APIs");
        return dto;
    }

    private JobExperience createEntity(Long id) {
        return new JobExperience("Company A", "Software Engineering",
                new Date(), new Date(),
                "Developer", "Worked on backend APIs") {{
            setId(id);
        }};
    }

    @Test
    void testSave() {
        JobExperienceDto dto = createDto();
        JobExperience savedEntity = createEntity(1L);

        when(repository.save(any(JobExperience.class))).thenReturn(savedEntity);

        JobExperienceDto result = jobExperienceService.save(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(JobExperience.class));
    }

    @Test
    void testUpdate_Success() {
        Long id = 1L;
        JobExperience existing = createEntity(id);
        JobExperienceDto updateDto = createDto();
        updateDto.setInstitution("Updated Company");

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(JobExperience.class))).thenReturn(existing);

        JobExperienceDto result = jobExperienceService.update(id, updateDto);

        assertNotNull(result);
        assertEquals("Updated Company", result.getInstitution());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(JobExperience.class));
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 99L;
        JobExperienceDto dto = createDto();

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> jobExperienceService.update(id, dto));

        assertEquals("Job experience not found", exception.getMessage());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetAll() {
        List<JobExperience> entities = List.of(createEntity(1L), createEntity(2L));
        when(repository.findAll()).thenReturn(entities);

        List<JobExperienceDto> result = jobExperienceService.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        jobExperienceService.delete(id);

        verify(repository, times(1)).deleteById(id);
    }
}
