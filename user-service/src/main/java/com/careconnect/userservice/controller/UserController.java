package com.careconnect.userservice.controller;

import com.careconnect.userservice.entity.AppUser;
import com.careconnect.userservice.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // READ: Get all users
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    // READ: Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = userServiceImpl.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // READ: Get users by role
    @GetMapping("/role/{role}")
    public List<AppUser> getUsersByRole(@PathVariable String role) {
        return userServiceImpl.getUsersByRole(role);
    }

    // CREATE: Create a new user
    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return userServiceImpl.addUser(user);
    }

    // UPDATE: Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser userDetails) {
        AppUser updatedUser = userServiceImpl.updateUser(id, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // DELETE: Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userServiceImpl.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
