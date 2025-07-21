package com.careconnect.userservice.service;

import com.careconnect.userservice.entity.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> getAllUsers();
    AppUser getUserById(Long id);
    List<AppUser> getUsersByRole(String role);
    AppUser addUser(AppUser user);
    AppUser updateUser(Long id, AppUser userDetails);
    boolean deleteUser(Long id);
}
