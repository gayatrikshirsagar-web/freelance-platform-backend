package com.freelance_platform.dto;

import lombok.Data;

@Data
public class AdminDashboardResponse {

    private long totalStudents;
    private long totalClients;
    private long totalProjects;
    private long activeProjects;
    private long completedProjects;
    private long pendingApplications;
    private long pendingStudentVerifications;
    private long pendingClientVerifications;
}