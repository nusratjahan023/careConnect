package com.careconnect.userservice.service;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.entity.Certification;
import com.careconnect.userservice.entity.CertificationDto;
import com.careconnect.userservice.repository.CertificationRepository;
import com.careconnect.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    public CertificationDto saveCertificate(CertificationDto dto) {
        AppUser user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("UserDetails not found"));

        Certification certification = new Certification();
        certification.setName(dto.getName());
        certification.setCompletionDate(dto.getCompletionDate());
        certification.setUserDetails(user.getUserDetails());

        Certification certification1 = certificationRepository.save(certification);
        dto.setId(certification1.getId());
        return dto;
    }

    public CertificationDto updateCertificate(CertificationDto dto) {

        Certification certification = certificationRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("UserDetails not found"));
        certification.setName(dto.getName());
        certification.setCompletionDate(dto.getCompletionDate());

        certificationRepository.save(certification);
        return dto;
    }

    public boolean deleteCertification(Long id) {
        if (certificationRepository.existsById(id)) {
            certificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

