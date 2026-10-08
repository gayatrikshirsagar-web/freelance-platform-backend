package com.freelance_platform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateGigRequest {

    private Integer userId;

    private Integer categoryId;

    private String title;

    private String description;

    private BigDecimal budgetMin;

    private BigDecimal budgetMax;

    private LocalDate deadline;

    private String requiredExperience;
}