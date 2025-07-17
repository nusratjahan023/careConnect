package com.careconnect.userservice.service;

import com.careconnect.userservice.entity.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto saveSkill(SkillDto skillDto);
    SkillDto updateSkill(Long id, SkillDto skillDto);
    List<SkillDto> getAllSkills();
    void deleteSkill(Long id);
}
