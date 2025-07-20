package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.dto.CertificationDto;
import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.entity.Certification;
import com.careconnect.userservice.entity.UserDetails;
import com.careconnect.userservice.repository.CertificationRepository;
import com.careconnect.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CertificationServiceTest {

    @InjectMocks
    private CertificationService certificationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CertificationRepository certificationRepository;

    private AppUser appUser;
    private Certification certification;
    private CertificationDto dto;

    @BeforeEach
    void setUp() {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(1L);

        appUser = new AppUser();
        appUser.setId(1L);
        appUser.setUserDetails(userDetails);

        certification = new Certification();
        certification.setId(100L);
        certification.setName("Java Certification");
        certification.setCompletionDate(LocalDate.of(2023, 1, 1).toString());
        certification.setUserDetails(userDetails);

        dto = new CertificationDto();
        dto.setUserId(1L);
        dto.setName("Java Certification");
        dto.setCompletionDate(LocalDate.of(2023, 1, 1).toString());
    }

    @Test
    void testSaveCertificate_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(certificationRepository.save(any(Certification.class))).thenAnswer(i -> {
            Certification saved = i.getArgument(0);
            saved.setId(100L);
            return saved;
        });

        CertificationDto result = certificationService.saveCertificate(dto);

        assertThat(result.getId()).isEqualTo(100L);
        verify(userRepository, times(1)).findById(1L);
        verify(certificationRepository, times(1)).save(any(Certification.class));
    }

    @Test
    void testSaveCertificate_userNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> certificationService.saveCertificate(dto));

        assertThat(exception.getMessage()).isEqualTo("UserDetails not found");
    }

    @Test
    void testUpdateCertificate_success() {
        dto.setId(100L);
        when(certificationRepository.findById(100L)).thenReturn(Optional.of(certification));
        when(certificationRepository.save(any(Certification.class))).thenReturn(certification);

        CertificationDto updated = certificationService.updateCertificate(dto);

        assertThat(updated.getName()).isEqualTo("Java Certification");
        verify(certificationRepository).save(certification);
    }

    @Test
    void testUpdateCertificate_certificationNotFound() {
        dto.setId(200L);
        when(certificationRepository.findById(200L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> certificationService.updateCertificate(dto));

        assertThat(exception.getMessage()).isEqualTo("UserDetails not found");
    }

    @Test
    void testDeleteCertification_success() {
        when(certificationRepository.existsById(100L)).thenReturn(true);

        boolean deleted = certificationService.deleteCertification(100L);

        assertThat(deleted).isTrue();
        verify(certificationRepository).deleteById(100L);
    }

    @Test
    void testDeleteCertification_notFound() {
        when(certificationRepository.existsById(200L)).thenReturn(false);

        boolean deleted = certificationService.deleteCertification(200L);

        assertThat(deleted).isFalse();
        verify(certificationRepository, never()).deleteById(anyLong());
    }
}
