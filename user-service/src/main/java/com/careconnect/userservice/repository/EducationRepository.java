package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
