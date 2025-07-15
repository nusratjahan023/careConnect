package com.careconnect.userservice.entity;


import com.careconnect.userservice.Enums.ProficiencyType;
import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProficiencyType proficiency;

    public Skill(String name, ProficiencyType proficiency) {
        this.name = name;
        this.proficiency = proficiency;
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
