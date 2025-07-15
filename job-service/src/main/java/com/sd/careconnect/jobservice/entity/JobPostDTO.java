package com.sd.careconnect.jobservice.entity;

import java.util.List;

public class JobPostDTO extends JobPost{
    private String message;
    private boolean success;
    private boolean globalError;
    private  String globalErrorMessage;
    private boolean canApply;
    private boolean canEdit;
    private List<JobApplication> applications;

    // Constructors
    public JobPostDTO (String message, boolean success, boolean globalError, String globalErrorMessage) {
        this.message = message;
        this.success = success;
        this.globalError = globalError;
        this.globalErrorMessage = globalErrorMessage;
    }

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isGlobalError() {
        return globalError;
    }

    public void setGlobalError(boolean globalError) {
        this.globalError = globalError;
    }

    public String getGlobalErrorMessage() {
        return globalErrorMessage;
    }

    public void setGlobalErrorMessage(String globalErrorMessage) {
        this.globalErrorMessage = globalErrorMessage;
    }

    public boolean isCanApply() {
        return canApply;
    }

    public void setCanApply(boolean canApply) {
        this.canApply = canApply;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }
}

