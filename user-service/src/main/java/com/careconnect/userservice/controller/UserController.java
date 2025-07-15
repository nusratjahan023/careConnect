package com.careconnect.userservice.controller;

import com.careconnect.userservice.entity.*;
import com.careconnect.userservice.service.CertificationService;
import com.careconnect.userservice.service.UserDetailsService;
import com.careconnect.userservice.service.UserServiceImpl;
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

    public UserController(UserServiceImpl userServiceImpl, UserDetailsService userDetailsService, CertificationService certificationService) {
        this.userServiceImpl = userServiceImpl;
        this.userDetailsService = userDetailsService;
        this.certificationService =certificationService;
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
}
