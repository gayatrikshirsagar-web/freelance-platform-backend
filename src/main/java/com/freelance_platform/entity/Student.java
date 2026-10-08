package com.freelance_platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;


    // ==========================================
    // USER RELATIONSHIP
    // ==========================================

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;


    // ==========================================
    // BASIC INFORMATION
    // ==========================================

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "course")
    private String course;

    @Column(name = "education")
    private String education;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location")
    private String location;


    // ==========================================
    // PROFILE PICTURE
    // ==========================================

    @Column(name = "profile_picture")
    private String profilePicture;


    // ==========================================
    // STUDENT INFORMATION
    // ==========================================

    @Column(name = "year_of_study")
    private Integer yearOfStudy;


    // IMPORTANT:
    // MySQL = DECIMAL(10,2)
    // Java = BigDecimal

    @Column(
            name = "hourly_rate",
            precision = 10,
            scale = 2
    )
    private BigDecimal hourlyRate;


    // ==========================================
    // AVAILABILITY
    // ==========================================

    @Column(name = "availability_status")
    private String availabilityStatus;


    // ==========================================
    // VERIFICATION
    // ==========================================

    @Column(name = "verification_status")
    private String verificationStatus;


    // ==========================================
    // CREATED DATE
    // ==========================================

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (availabilityStatus == null) {
            availabilityStatus = "AVAILABLE";
        }

        if (verificationStatus == null) {
            verificationStatus = "PENDING";
        }
    }
}