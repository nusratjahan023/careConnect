package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.dto.UserDetailsDTO;
import com.careconnect.userservice.entity.*;
import com.careconnect.userservice.repository.LanguageRepository;
import com.careconnect.userservice.repository.UserDetailsRepository;
import com.careconnect.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository appUserRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public void saveUserDetails(Long userId, UserDetailsDTO dto) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = mapFromDTO(dto);
        user.setUserDetails(userDetails); // Link to user
        appUserRepository.save(user);
    }

    private UserDetails mapFromDTO(UserDetailsDTO dto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setBio(dto.getBio());
        userDetails.setCertifications(dto.getCertifications());
        userDetails.setSkills(dto.getSkills());
        userDetails.setEducations(dto.getEducations());
        userDetails.setJobExperiences(dto.getJobExperiences());
        userDetails.setLanguages(dto.getLanguages());
        return userDetails;
    }
}

