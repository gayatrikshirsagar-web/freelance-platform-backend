package com.freelance_platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "gig_id", nullable = false)
    private Integer gigId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "proposed_price", precision = 10, scale = 2)
    private BigDecimal proposedPrice;

    @Column(name = "estimated_days")
    private Integer estimatedDays;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @PrePersist
    protected void onCreate() {

        if (applicationStatus == null) {
            applicationStatus = "PENDING";
        }

        if (appliedAt == null) {
            appliedAt = LocalDateTime.now();
        }
    }
}