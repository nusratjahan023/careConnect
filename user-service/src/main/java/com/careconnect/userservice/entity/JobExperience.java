package com.careconnect.userservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "job_experience")
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;
    private String degree;
    private Date startDate;
    private Date endDate;
    private String designation;
    private String description;

    public JobExperience() {
    }

    public JobExperience(String institution, String degree, Date startDate, Date endDate, String designation, String description) {
        this.institution = institution;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
        this.designation = designation;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
