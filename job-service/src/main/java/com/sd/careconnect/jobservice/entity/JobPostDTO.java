package com.sd.careconnect.jobservice.entity;

import com.sd.careconnect.jobservice.Enums.JobStatus;

import java.util.List;

public class JobPostDTO extends JobPost{
    private String message;
    private boolean success;
    private boolean globalError;
    private  String globalErrorMessage;
    private boolean canApply;
    private boolean canEdit;
    private  boolean canComplete;
    private boolean canMakePayment;
    private boolean canAddReview;
    private boolean canClose;
    private boolean canViewApplicantList;
    private boolean canViewPaymentStatus;
    private List<JobApplication> applications;

    public JobPostDTO (String message, boolean success, boolean globalError, String globalErrorMessage) {
        this.message = message;
        this.success = success;
        this.globalError = globalError;
        this.globalErrorMessage = globalErrorMessage;
    }

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

    public boolean isCanViewApplicantList() {
        return canViewApplicantList;
    }

    public void setCanViewApplicantList(boolean canViewApplicantList) {
        this.canViewApplicantList = canViewApplicantList;
    }

    public boolean isCanComplete() {
        return canComplete;
    }

    public void setCanComplete(boolean canComplete) {
        this.canComplete = canComplete;
    }

    public boolean isCanMakePayment() {
        return canMakePayment;
    }

    public void setCanMakePayment(boolean canMakePayment) {
        this.canMakePayment = canMakePayment;
    }

    public boolean isCanAddReview() {
        return canAddReview;
    }

    public void setCanAddReview(boolean canAddReview) {
        this.canAddReview = canAddReview;
    }

    public boolean isCanClose() {
        return canClose;
    }

    public void setCanClose(boolean canClose) {
        this.canClose = canClose;
    }

    public boolean isCanViewPaymentStatus() {
        return canViewPaymentStatus;
    }

    public void setCanViewPaymentStatus(boolean canViewPaymentStatus) {
        this.canViewPaymentStatus = canViewPaymentStatus;
    }
}

