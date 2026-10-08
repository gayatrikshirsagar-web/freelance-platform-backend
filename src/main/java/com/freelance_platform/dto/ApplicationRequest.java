package com.freelance_platform.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplicationRequest {

    private Integer gigId;

    private Integer userId;

    private String coverLetter;

    private BigDecimal proposedPrice;

    private Integer estimatedDays;
}