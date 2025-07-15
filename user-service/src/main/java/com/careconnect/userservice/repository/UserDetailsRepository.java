package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}