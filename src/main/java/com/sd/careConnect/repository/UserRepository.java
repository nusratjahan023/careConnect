package com.sd.careConnect.repository;

import com.sd.careConnect.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    // Optional: custom methods, e.g.:
    List<AppUser> findByRole(String role);
}
