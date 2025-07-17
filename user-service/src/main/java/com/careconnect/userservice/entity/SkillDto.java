package com.careconnect.userservice.entity;

import com.careconnect.userservice.Enums.ProficiencyType;

public class SkillDto {
    private Long id;
    private String name;
    private ProficiencyType proficiency;
    private Long userId;

    public SkillDto() {}

    public SkillDto(Long id, String name, ProficiencyType proficiency) {
        this.id = id;
        this.name = name;
        this.proficiency = proficiency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProficiencyType getProficiency() {
        return proficiency;
    }

    public void setProficiency(ProficiencyType proficiency) {
        this.proficiency = proficiency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
