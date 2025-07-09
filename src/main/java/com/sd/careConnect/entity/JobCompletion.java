package com.sd.careConnect.entity;

import java.time.LocalDateTime;

public class JobCompletion {
    private Long id;
    private JobPost jobPost;
    private AppUser caregiver;
    private LocalDateTime completionTime;
    private String caregiverNotes;
    private boolean isVerifiedByClient;
}

