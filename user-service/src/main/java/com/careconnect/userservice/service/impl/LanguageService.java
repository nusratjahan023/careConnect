package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.Language;
import com.careconnect.userservice.dto.LanguageDto;
import com.careconnect.userservice.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageDto saveLanguage(LanguageDto languageDto) {
        Language language = new Language(languageDto.getName(), languageDto.getProficiency());
        Language saved = languageRepository.save(language);

        LanguageDto savedDto = new LanguageDto();
        savedDto.setId(saved.getId());
        savedDto.setName(saved.getName());
        savedDto.setProficiency(saved.getProficiency());

        return savedDto;
    }

    public LanguageDto updateLanguage(Long id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);

        if (optionalLanguage.isPresent()) {
            Language language = optionalLanguage.get();
            language.setName(languageDto.getName());
            language.setProficiency(languageDto.getProficiency());

            Language updated = languageRepository.save(language);

            LanguageDto updatedDto = new LanguageDto();
            updatedDto.setId(updated.getId());
            updatedDto.setName(updated.getName());
            updatedDto.setProficiency(updated.getProficiency());

            return updatedDto;
        } else {
            throw new RuntimeException("Language with ID " + id + " not found.");
        }
    }
}
