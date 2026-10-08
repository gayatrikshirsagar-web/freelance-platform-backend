package com.freelance_platform.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ClientDashboardResponse {

    // ==============================
    // CLIENT INFORMATION
    // ==============================

    private String clientName;
    private String companyName;


    // ==============================
    // DASHBOARD STATISTICS
    // ==============================

    private long activeProjects;
    private long postedProjects;
    private long applications;

    private BigDecimal totalSpent;

    private long unreadNotifications;


    // ==============================
    // DASHBOARD LISTS
    // ==============================

    private List<ActiveProject> activeProjectList;

    private List<RecentApplication> recentApplications;

    private List<RecommendedFreelancer> recommendedFreelancers;


    // ==============================
    // GETTERS AND SETTERS
    // ==============================

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public long getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(long activeProjects) {
        this.activeProjects = activeProjects;
    }


    public long getPostedProjects() {
        return postedProjects;
    }

    public void setPostedProjects(long postedProjects) {
        this.postedProjects = postedProjects;
    }


    public long getApplications() {
        return applications;
    }

    public void setApplications(long applications) {
        this.applications = applications;
    }


    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }


    public long getUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(long unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }


    public List<ActiveProject> getActiveProjectList() {
        return activeProjectList;
    }

    public void setActiveProjectList(
            List<ActiveProject> activeProjectList) {

        this.activeProjectList = activeProjectList;
    }


    public List<RecentApplication> getRecentApplications() {
        return recentApplications;
    }

    public void setRecentApplications(
            List<RecentApplication> recentApplications) {

        this.recentApplications = recentApplications;
    }


    public List<RecommendedFreelancer> getRecommendedFreelancers() {
        return recommendedFreelancers;
    }

    public void setRecommendedFreelancers(
            List<RecommendedFreelancer> recommendedFreelancers) {

        this.recommendedFreelancers =
                recommendedFreelancers;
    }


    // =====================================================
    // ACTIVE PROJECT DTO
    // =====================================================

    public static class ActiveProject {

        private Integer gigId;
        private String title;
        private String status;
        private LocalDate deadline;
        private BigDecimal budgetMin;
        private BigDecimal budgetMax;

        private Integer studentId;
        private String studentName;


        public Integer getGigId() {
            return gigId;
        }

        public void setGigId(Integer gigId) {
            this.gigId = gigId;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public LocalDate getDeadline() {
            return deadline;
        }

        public void setDeadline(LocalDate deadline) {
            this.deadline = deadline;
        }


        public BigDecimal getBudgetMin() {
            return budgetMin;
        }

        public void setBudgetMin(BigDecimal budgetMin) {
            this.budgetMin = budgetMin;
        }


        public BigDecimal getBudgetMax() {
            return budgetMax;
        }

        public void setBudgetMax(BigDecimal budgetMax) {
            this.budgetMax = budgetMax;
        }


        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }


        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }


    // =====================================================
    // RECENT APPLICATION DTO
    // =====================================================

    public static class RecentApplication {

        private Integer applicationId;
        private Integer gigId;
        private String projectTitle;

        private Integer studentId;
        private String studentName;

        private BigDecimal proposedPrice;
        private Integer estimatedDays;

        private String applicationStatus;
        private LocalDateTime appliedAt;


        public Integer getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(Integer applicationId) {
            this.applicationId = applicationId;
        }


        public Integer getGigId() {
            return gigId;
        }

        public void setGigId(Integer gigId) {
            this.gigId = gigId;
        }


        public String getProjectTitle() {
            return projectTitle;
        }

        public void setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
        }


        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }


        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }


        public BigDecimal getProposedPrice() {
            return proposedPrice;
        }

        public void setProposedPrice(BigDecimal proposedPrice) {
            this.proposedPrice = proposedPrice;
        }


        public Integer getEstimatedDays() {
            return estimatedDays;
        }

        public void setEstimatedDays(Integer estimatedDays) {
            this.estimatedDays = estimatedDays;
        }


        public String getApplicationStatus() {
            return applicationStatus;
        }

        public void setApplicationStatus(
                String applicationStatus) {

            this.applicationStatus = applicationStatus;
        }


        public LocalDateTime getAppliedAt() {
            return appliedAt;
        }

        public void setAppliedAt(LocalDateTime appliedAt) {
            this.appliedAt = appliedAt;
        }
    }


    // =====================================================
    // RECOMMENDED FREELANCER DTO
    // =====================================================

    public static class RecommendedFreelancer {

        private Integer studentId;
        private Integer userId;

        private String name;
        private String collegeName;
        private String course;
        private String location;

        private BigDecimal hourlyRate;

        private String availabilityStatus;
        private String verificationStatus;


        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }


        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }


        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }


        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }


        public BigDecimal getHourlyRate() {
            return hourlyRate;
        }

        public void setHourlyRate(BigDecimal hourlyRate) {
            this.hourlyRate = hourlyRate;
        }


        public String getAvailabilityStatus() {
            return availabilityStatus;
        }

        public void setAvailabilityStatus(
                String availabilityStatus) {

            this.availabilityStatus = availabilityStatus;
        }


        public String getVerificationStatus() {
            return verificationStatus;
        }

        public void setVerificationStatus(
                String verificationStatus) {

            this.verificationStatus = verificationStatus;
        }
    }
}