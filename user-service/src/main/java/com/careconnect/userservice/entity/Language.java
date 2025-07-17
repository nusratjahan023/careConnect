package com.careconnect.userservice.entity;

import com.careconnect.userservice.Enums.ProficiencyType;
import jakarta.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ProficiencyType proficiency;

    public Language() {
    }

    public Language(String name, ProficiencyType proficiency) {
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
}
