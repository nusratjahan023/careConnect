package com.careconnect.userservice.entity;

import java.util.List;

public class UserDetailsDTO {
    private String bio;
    private List<Certification> certifications;
    private List<Skill> skills;
    private List<Education> educations;
    private List<JobExperience> jobExperiences;
    private List<Language> languages;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<JobExperience> getJobExperiences() {
        return jobExperiences;
    }

    public void setJobExperiences(List<JobExperience> jobExperiences) {
        this.jobExperiences = jobExperiences;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}

