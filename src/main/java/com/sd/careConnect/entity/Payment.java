package com.sd.careConnect.entity;

import com.sd.careConnect.Enums.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private JobPost jobPost;
    private AppUser client;
    private AppUser caregiver;
    private Double amount;
    private PaymentStatus status; // PENDING, COMPLETED, FAILED
    private LocalDateTime paymentTime;
}

