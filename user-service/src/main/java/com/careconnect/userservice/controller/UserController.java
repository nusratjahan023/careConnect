package com.careconnect.userservice.controller;

import com.careconnect.userservice.entity.*;
import com.careconnect.userservice.service.*;
import com.careconnect.userservice.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final UserDetailsService userDetailsService;
    private final CertificationService certificationService;
    private final LanguageService languageService;
    private final EducationServiceImpl educationService;
    private final ReviewServiceImpl reviewService;

    public UserController(UserServiceImpl userServiceImpl, UserDetailsService userDetailsService, CertificationService certificationService, LanguageService languageService, EducationServiceImpl educationService, ReviewServiceImpl reviewService) {
        this.userServiceImpl = userServiceImpl;
        this.userDetailsService = userDetailsService;
        this.certificationService =certificationService;
        this.languageService = languageService;
        this.educationService = educationService;
        this.reviewService = reviewService;
    }

//    @GetMapping("/profile/{id}")
//    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable Long id) {
//        UserDetailsDTO dto = userDetailsService.getUserDetailsByUserId(id);
//        return ResponseEntity.ok(dto);
//    }

    @PostMapping("/{id}/details")
    public ResponseEntity<Void> saveUserDetails(@PathVariable Long id, @RequestBody UserDetailsDTO dto) {
        userDetailsService.saveUserDetails(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/certificate")
    public ResponseEntity<CertificationDto> saveCertificate(@RequestBody CertificationDto dto) {
        CertificationDto saved = certificationService.saveCertificate(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/certificate/{id}")
    public ResponseEntity<CertificationDto> updateCertificate(@RequestBody CertificationDto dto) {
        CertificationDto updated = certificationService.updateCertificate(dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/language")
    public ResponseEntity<LanguageDto> saveLanguage(@RequestBody LanguageDto dto) {
        LanguageDto saved = languageService.saveLanguage(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/language/{id}")
    public ResponseEntity<LanguageDto> updateLanguage(@RequestBody LanguageDto dto) {
        LanguageDto updated = languageService.updateLanguage(dto.getId(), dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/education")
    public ResponseEntity<EducationDto> saveEducation(@RequestBody EducationDto dto) {
        EducationDto saved = educationService.saveEducation(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<EducationDto> updateEducation(@PathVariable Long id, @RequestBody EducationDto dto) {
        EducationDto updated = educationService.updateEducation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/reviews/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto dto) {
        ReviewDto saved = reviewService.saveReview(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = userServiceImpl.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/role/{role}")
    public List<AppUser> getUsersByRole(@PathVariable String role) {
        return userServiceImpl.getUsersByRole(role);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AppUserDTO> getProfile(@PathVariable Long id) {
        AppUserDTO userDto = userServiceImpl.getUserProfile(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", true,
                            "errorMessage", "Password and Confirm Password do not match"
                    ));
        }
        user.setUserDetails(new UserDetails());
        AppUser savedUser = userServiceImpl.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getPassword().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", true,
                            "errorMessage", "Password is required"
                    ));
        }

        if (loginRequest.getEmail().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", true,
                            "errorMessage", "Email is required"
                    ));
        }
        AppUser authenticatedUser = userServiceImpl.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity
                    .ok(authenticatedUser);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", true,
                            "errorMessage", "Auth failed"
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser userDetails) {
        AppUser updatedUser = userServiceImpl.updateUser(id, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userServiceImpl.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/certification/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) {
        boolean deleted = certificationService.deleteCertification(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/education/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        boolean deleted = educationService.deleteEducation(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
