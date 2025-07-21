package com.careconnect.userservice.repository;

import com.careconnect.userservice.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long>{
}