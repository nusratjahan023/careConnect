package com.careconnect.userservice.service.impl;

import com.careconnect.userservice.entity.SkillDto;
import com.careconnect.userservice.entity.Skill;
import com.careconnect.userservice.repository.SkillRepository;
import com.careconnect.userservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDto saveSkill(SkillDto skillDto) {
        Skill skill = new Skill(skillDto.getName(), skillDto.getProficiency());
        Skill savedSkill = skillRepository.save(skill);
        skillDto.setId(savedSkill.getId());
        return skillDto;
    }

    @Override
    public SkillDto updateSkill(Long id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));

        skill.setName(skillDto.getName());
        skill.setProficiency(skillDto.getProficiency());
        Skill updatedSkill = skillRepository.save(skill);

        return new SkillDto(updatedSkill.getId(), updatedSkill.getName(), updatedSkill.getProficiency());
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillDto(skill.getId(), skill.getName(), skill.getProficiency()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
