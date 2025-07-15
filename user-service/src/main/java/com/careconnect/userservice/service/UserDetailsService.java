package com.careconnect.userservice.service;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.entity.UserDetails;
import com.careconnect.userservice.entity.UserDetailsDTO;
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

    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = user.getUserDetails(); // Assuming 1:1 relation
        return mapToDTO(userDetails);
    }

    public void saveUserDetails(Long userId, UserDetailsDTO dto) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = mapFromDTO(dto);
        user.setUserDetails(userDetails); // Link to user
        appUserRepository.save(user);
    }

    private UserDetailsDTO mapToDTO(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setBio(userDetails.getBio());
        dto.setCertifications(userDetails.getCertifications());
        dto.setSkills(userDetails.getSkills());
        dto.setEducations(userDetails.getEducations());
        dto.setJobExperiences(userDetails.getJobExperiences());
        dto.setLanguages(userDetails.getLanguages());
        return dto;
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

