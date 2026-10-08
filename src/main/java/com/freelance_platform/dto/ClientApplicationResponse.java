package com.freelance_platform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ClientApplicationResponse {

    private Integer applicationId;

    private Integer gigId;

    private String projectTitle;

    private Integer studentId;

    private String coverLetter;

    private BigDecimal proposedPrice;

    private Integer estimatedDays;

    private String applicationStatus;

    private LocalDateTime appliedAt;

    private LocalDateTime reviewedAt;
}