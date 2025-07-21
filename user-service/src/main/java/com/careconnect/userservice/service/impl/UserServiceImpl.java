package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.enums.Role;
import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.dto.AppUserDTO;
import com.careconnect.userservice.entity.UserDetails;
import com.careconnect.userservice.dto.UserDetailsDTO;
import com.careconnect.userservice.repository.UserRepository;
import com.careconnect.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> getUsersByRole(String roleString) {
        Role role = Role.valueOf(roleString.toUpperCase());
        return userRepository.findByRole(role);
    }

    @Override
    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser updateUser(Long id, AppUser userDetails) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AppUserDTO getUserProfile(Long id) {
        AppUser user = userRepository.findById(id).orElseThrow();

        AppUserDTO dto = new AppUserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setLastName(user.getLastName());
        dto.setAddress(user.getAddress());
        dto.setRating(user.getRating());

        UserDetails ud = user.getUserDetails();
        if (ud != null) {
            UserDetailsDTO udd = new UserDetailsDTO();
            udd.setBio(ud.getBio());
            udd.setCertifications(ud.getCertifications());
            udd.setSkills(ud.getSkills());
            udd.setJobExperiences(ud.getJobExperiences());
            udd.setEducations(ud.getEducations());
            udd.setLanguages(ud.getLanguages());
            dto.setUserDetails(udd);
        }

        return dto;
    }

    public AppUser authenticate(String email, String password) {
        AppUser appUser = userRepository.findByEmail(email);
        if  (appUser != null && appUser.getPassword().equals(password)){
            return appUser;
        } else {
            return null;
        }
    }
}
