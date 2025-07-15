package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByRole(String role);
}
