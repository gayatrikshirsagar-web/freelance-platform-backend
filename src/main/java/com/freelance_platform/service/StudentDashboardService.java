package com.freelance_platform.service;

import com.freelance_platform.dto.StudentDashboardResponse;
import com.freelance_platform.entity.Application;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;
import com.freelance_platform.repository.ApplicationRepository;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.GigRepository;
import com.freelance_platform.repository.NotificationRepository;
import com.freelance_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDashboardService {

    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;
    private final GigRepository gigRepository;
    private final ClientRepository clientRepository;
    private final NotificationRepository notificationRepository;

    public StudentDashboardService(
            StudentRepository studentRepository,
            ApplicationRepository applicationRepository,
            GigRepository gigRepository,
            ClientRepository clientRepository,
            NotificationRepository notificationRepository) {

        this.studentRepository = studentRepository;
        this.applicationRepository = applicationRepository;
        this.gigRepository = gigRepository;
        this.clientRepository = clientRepository;
        this.notificationRepository = notificationRepository;
    }

    public StudentDashboardResponse getDashboard(
            Integer userId) {

        Student student = studentRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + userId
                        )
                );

        Integer studentId =
                student.getStudentId();
        
        StudentDashboardResponse response =
                new StudentDashboardResponse();
        response.setStudentId(studentId);
        // =====================================================
        // STUDENT INFORMATION
        // =====================================================

        if (student.getUser() != null) {

            response.setStudentName(
                    student.getUser().getName()
            );

            response.setEmail(
                    student.getUser().getEmail()
            );
        }

        response.setProfileCompletion(
                calculateProfileCompletion(student)
        );

        // =====================================================
        // APPLICATIONS
        // =====================================================

        List<Application> applications =
                applicationRepository.findByStudentId(
                        studentId
                );

        response.setTotalApplications(
                applications.size()
        );

        List<Application> recentApplications =
                applications.stream()
                        .sorted(
                                Comparator.comparing(
                                        Application::getAppliedAt,
                                        Comparator.nullsLast(
                                                Comparator.reverseOrder()
                                        )
                                )
                        )
                        .limit(3)
                        .collect(Collectors.toList());

        for (Application application :
                recentApplications) {

            StudentDashboardResponse.RecentApplication dto =
                    new StudentDashboardResponse
                            .RecentApplication();

            dto.setApplicationId(
                    application.getApplicationId()
            );

            dto.setGigId(
                    application.getGigId()
            );

            dto.setProposedPrice(
                    application.getProposedPrice()
            );

            dto.setEstimatedDays(
                    application.getEstimatedDays()
            );

            dto.setApplicationStatus(
                    application.getApplicationStatus()
            );

            dto.setAppliedAt(
                    application.getAppliedAt()
            );

            Gig gig = gigRepository
                    .findById(application.getGigId())
                    .orElse(null);

            if (gig != null) {

                dto.setProjectTitle(
                        gig.getTitle()
                );

                Client client = clientRepository
                        .findById(gig.getClientId())
                        .orElse(null);

                if (client != null) {

                    dto.setClientName(
                            client.getCompanyName()
                    );
                }
            }

            response.getRecentApplications()
                    .add(dto);
        }

        // =====================================================
        // ACTIVE PROJECTS
        // =====================================================

        List<Gig> activeGigs =
                gigRepository
                        .findByAssignedStudentIdAndStatus(
                                studentId,
                                "IN_PROGRESS"
                        );

        response.setActiveProjectsCount(
                activeGigs.size()
        );

        for (Gig gig : activeGigs) {

            StudentDashboardResponse.ActiveProject dto =
                    new StudentDashboardResponse.ActiveProject();

            dto.setGigId(
                    gig.getGigId()
            );

            dto.setTitle(
                    gig.getTitle()
            );

            dto.setStatus(
                    gig.getStatus()
            );

            dto.setDeadline(
                    gig.getDeadline()
            );

            dto.setBudgetMin(
                    gig.getBudgetMin()
            );

            dto.setBudgetMax(
                    gig.getBudgetMax()
            );

            Client client = clientRepository
                    .findById(gig.getClientId())
                    .orElse(null);

            if (client != null) {

                dto.setClientName(
                        client.getCompanyName()
                );
            }

            response.getActiveProjects()
                    .add(dto);
        }

        // =====================================================
        // COMPLETED PROJECTS
        // =====================================================

        List<Gig> completedGigs =
                gigRepository
                        .findByAssignedStudentIdAndStatus(
                                studentId,
                                "COMPLETED"
                        );

        response.setCompletedProjectsCount(
                completedGigs.size()
        );

        // =====================================================
        // EARNINGS
        // =====================================================
        //
        // There is currently no payment/transaction table.
        // Do NOT calculate earnings from the project budget,
        // because a budget is not the same thing as money paid.
        //

        response.setTotalEarnings(
                BigDecimal.ZERO
        );

        // =====================================================
        // UNREAD NOTIFICATIONS
        // =====================================================

        response.setUnreadNotifications(
                notificationRepository
                        .findByUserIdAndIsRead(
                                userId,
                                false
                        )
                        .size()
        );

        // =====================================================
        // RECOMMENDED PROJECTS
        // =====================================================
        //
        // At the moment the database does not contain a
        // student-skill/project-skill matching structure.
        // Therefore "recommended" means latest OPEN gigs.
        //

        List<Gig> openGigs =
                gigRepository.findByStatus(
                        "OPEN"
                );

        openGigs.stream()
                .limit(3)
                .forEach(gig -> {

                    StudentDashboardResponse
                            .RecommendedProject dto =
                            new StudentDashboardResponse
                                    .RecommendedProject();

                    dto.setGigId(
                            gig.getGigId()
                    );

                    dto.setCategoryId(
                            gig.getCategoryId()
                    );

                    dto.setTitle(
                            gig.getTitle()
                    );

                    dto.setDescription(
                            gig.getDescription()
                    );

                    dto.setBudgetMin(
                            gig.getBudgetMin()
                    );

                    dto.setBudgetMax(
                            gig.getBudgetMax()
                    );

                    dto.setDeadline(
                            gig.getDeadline()
                    );

                    dto.setRequiredExperience(
                            gig.getRequiredExperience()
                    );

                    dto.setStatus(
                            gig.getStatus()
                    );

                    response.getRecommendedProjects()
                            .add(dto);
                });

        return response;
    }

    // =====================================================
    // PROFILE COMPLETION
    // =====================================================

    private int calculateProfileCompletion(
            Student student) {

        int totalFields = 8;
        int completedFields = 0;

        if (student.getCollegeName() != null &&
                !student.getCollegeName().isBlank()) {
            completedFields++;
        }

        if (student.getCourse() != null &&
                !student.getCourse().isBlank()) {
            completedFields++;
        }

        if (student.getEducation() != null &&
                !student.getEducation().isBlank()) {
            completedFields++;
        }

        if (student.getBio() != null &&
                !student.getBio().isBlank()) {
            completedFields++;
        }

        if (student.getLocation() != null &&
                !student.getLocation().isBlank()) {
            completedFields++;
        }

        if (student.getHourlyRate() != null) {
            completedFields++;
        }

        if (student.getYearOfStudy() != null) {
            completedFields++;
        }

        if (student.getProfilePicture() != null &&
                !student.getProfilePicture().isBlank()) {
            completedFields++;
        }

        return Math.round(
                (completedFields * 100f) /
                        totalFields
        );
    }
}
