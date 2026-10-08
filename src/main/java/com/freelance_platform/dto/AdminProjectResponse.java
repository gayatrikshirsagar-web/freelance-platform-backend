package com.freelance_platform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdminProjectResponse {

    private Integer gigId;
    private Integer clientId;
    private String clientName;

    private String title;
    private String description;

    private BigDecimal budgetMin;
    private BigDecimal budgetMax;

    private LocalDate deadline;

    private String requiredExperience;
    private String status;

    private Integer assignedStudentId;
}