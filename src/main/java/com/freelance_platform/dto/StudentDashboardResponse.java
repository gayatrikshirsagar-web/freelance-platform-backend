package com.freelance_platform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDashboardResponse {
	private Integer studentId;
    private String studentName;
    private String email;

    private Integer profileCompletion;

    private long totalApplications;
    private long activeProjectsCount;
    private long completedProjectsCount;

    /*
     * There is currently no payments/transactions table in the project.
     * Therefore this remains 0 until payment tracking is implemented.
     */
    private BigDecimal totalEarnings = BigDecimal.ZERO;

    private long unreadNotifications;

    private List<RecentApplication> recentApplications =
            new ArrayList<>();

    private List<ActiveProject> activeProjects =
            new ArrayList<>();

    private List<RecommendedProject> recommendedProjects =
            new ArrayList<>();

    @Data
    public static class RecentApplication {

        private Integer applicationId;
        private Integer gigId;

        private String projectTitle;
        private String clientName;

        private BigDecimal proposedPrice;
        private Integer estimatedDays;

        private String applicationStatus;
        private LocalDateTime appliedAt;
    }

    @Data
    public static class ActiveProject {
    	private Integer studentId;
        private Integer gigId;
        private String title;
        private String status;

        private String clientName;

        private LocalDate deadline;

        private BigDecimal budgetMin;
        private BigDecimal budgetMax;
    }

    @Data
    public static class RecommendedProject {
    	private Integer studentId;
        private Integer gigId;
        private Integer categoryId;

        private String title;
        private String description;

        private BigDecimal budgetMin;
        private BigDecimal budgetMax;

        private LocalDate deadline;
        private String requiredExperience;
        private String status;
    }
}
