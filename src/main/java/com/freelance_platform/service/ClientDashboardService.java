package com.freelance_platform.service;

import com.freelance_platform.dto.ClientDashboardResponse;
import com.freelance_platform.dto.ClientDashboardResponse.ActiveProject;
import com.freelance_platform.dto.ClientDashboardResponse.RecentApplication;
import com.freelance_platform.dto.ClientDashboardResponse.RecommendedFreelancer;

import com.freelance_platform.entity.Application;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;
import com.freelance_platform.entity.User;

import com.freelance_platform.repository.ApplicationRepository;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.GigRepository;
import com.freelance_platform.repository.NotificationRepository;
import com.freelance_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClientDashboardService {

    private final ClientRepository clientRepository;
    private final GigRepository gigRepository;
    private final ApplicationRepository applicationRepository;
    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;

    public ClientDashboardService(
            ClientRepository clientRepository,
            GigRepository gigRepository,
            ApplicationRepository applicationRepository,
            NotificationRepository notificationRepository,
            StudentRepository studentRepository) {

        this.clientRepository = clientRepository;
        this.gigRepository = gigRepository;
        this.applicationRepository = applicationRepository;
        this.notificationRepository = notificationRepository;
        this.studentRepository = studentRepository;
    }


    // =====================================================
    // MAIN DASHBOARD METHOD
    // =====================================================

    public ClientDashboardResponse getDashboard(
            Integer userId) {

        // -------------------------------------------------
        // 1. FIND CLIENT
        // -------------------------------------------------

        Client client =
                clientRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Client profile not found for user: "
                                                + userId
                                )
                        );


        Integer clientId =
                client.getClientId();

        User user =
                client.getUser();


        // -------------------------------------------------
        // 2. GET ALL CLIENT PROJECTS
        // -------------------------------------------------

        List<Gig> clientGigs =
                gigRepository.findByClientId(clientId);


        // -------------------------------------------------
        // 3. BASIC CLIENT INFORMATION
        // -------------------------------------------------

        ClientDashboardResponse response =
                new ClientDashboardResponse();

        response.setClientName(
                user.getName()
        );

        response.setCompanyName(
                client.getCompanyName()
        );


        // -------------------------------------------------
        // 4. POSTED PROJECT COUNT
        // -------------------------------------------------

        response.setPostedProjects(
                clientGigs.size()
        );


        // -------------------------------------------------
        // 5. ACTIVE PROJECTS
        // -------------------------------------------------

        List<Gig> activeGigs =
                gigRepository.findByClientIdAndStatus(
                        clientId,
                        "IN_PROGRESS"
                );

        response.setActiveProjects(
                activeGigs.size()
        );


        // -------------------------------------------------
        // 6. GET GIG IDs
        // -------------------------------------------------

        List<Integer> gigIds =
                new ArrayList<>();

        for (Gig gig : clientGigs) {

            gigIds.add(
                    gig.getGigId()
            );
        }


        // -------------------------------------------------
        // 7. GET ALL APPLICATIONS
        // -------------------------------------------------

        List<Application> applications;

        if (gigIds.isEmpty()) {

            applications =
                    Collections.emptyList();

        } else {

            applications =
                    applicationRepository
                            .findByGigIdInOrderByAppliedAtDesc(
                                    gigIds
                            );
        }


        response.setApplications(
                applications.size()
        );


        // -------------------------------------------------
        // 8. UNREAD NOTIFICATIONS
        // -------------------------------------------------

        long unreadNotifications =
                notificationRepository
                        .findByUserIdAndIsRead(
                                userId,
                                false
                        )
                        .size();

        response.setUnreadNotifications(
                unreadNotifications
        );


        // -------------------------------------------------
        // 9. ACTIVE PROJECT LIST
        // -------------------------------------------------

        List<ActiveProject> activeProjectList =
                new ArrayList<>();

        for (Gig gig : activeGigs) {

            ActiveProject project =
                    new ActiveProject();

            project.setGigId(
                    gig.getGigId()
            );

            project.setTitle(
                    gig.getTitle()
            );

            project.setStatus(
                    gig.getStatus()
            );

            project.setDeadline(
                    gig.getDeadline()
            );

            project.setBudgetMin(
                    gig.getBudgetMin()
            );

            project.setBudgetMax(
                    gig.getBudgetMax()
            );


            // ---------------------------------------------
            // Assigned freelancer
            // ---------------------------------------------

            Integer studentId =
                    gig.getAssignedStudentId();

            project.setStudentId(
                    studentId
            );


            if (studentId != null) {

                Student student =
                        studentRepository
                                .findById(studentId)
                                .orElse(null);

                if (student != null
                        && student.getUser() != null) {

                    project.setStudentName(
                            student
                                    .getUser()
                                    .getName()
                    );
                }
            }


            activeProjectList.add(
                    project
            );
        }


        response.setActiveProjectList(
                activeProjectList
        );


        // -------------------------------------------------
        // 10. RECENT APPLICATIONS
        // -------------------------------------------------

        List<RecentApplication> recentApplications =
                new ArrayList<>();

        int applicationLimit =
                Math.min(
                        applications.size(),
                        5
                );


        for (int i = 0;
             i < applicationLimit;
             i++) {

            Application application =
                    applications.get(i);


            RecentApplication recent =
                    new RecentApplication();


            recent.setApplicationId(
                    application.getApplicationId()
            );

            recent.setGigId(
                    application.getGigId()
            );

            recent.setStudentId(
                    application.getStudentId()
            );

            recent.setProposedPrice(
                    application.getProposedPrice()
            );

            recent.setEstimatedDays(
                    application.getEstimatedDays()
            );

            recent.setApplicationStatus(
                    application.getApplicationStatus()
            );

            recent.setAppliedAt(
                    application.getAppliedAt()
            );


            // ---------------------------------------------
            // Find project title
            // ---------------------------------------------

            for (Gig gig : clientGigs) {

                if (gig.getGigId()
                        .equals(application.getGigId())) {

                    recent.setProjectTitle(
                            gig.getTitle()
                    );

                    break;
                }
            }


            // ---------------------------------------------
            // Find student name
            // ---------------------------------------------

            Student student =
                    studentRepository
                            .findById(
                                    application.getStudentId()
                            )
                            .orElse(null);

            if (student != null
                    && student.getUser() != null) {

                recent.setStudentName(
                        student
                                .getUser()
                                .getName()
                );
            }


            recentApplications.add(
                    recent
            );
        }


        response.setRecentApplications(
                recentApplications
        );


        // -------------------------------------------------
        // 11. RECOMMENDED FREELANCERS
        // -------------------------------------------------

        List<Student> students =
                studentRepository
                        .findAll();

        List<RecommendedFreelancer>
                recommendedFreelancers =
                new ArrayList<>();


        for (Student student : students) {

            // Only show available students
            if (!"AVAILABLE".equals(
                    student.getAvailabilityStatus()
            )) {
                continue;
            }


            RecommendedFreelancer freelancer =
                    new RecommendedFreelancer();


            freelancer.setStudentId(
                    student.getStudentId()
            );


            if (student.getUser() != null) {

                freelancer.setUserId(
                        student
                                .getUser()
                                .getUserId()
                );

                freelancer.setName(
                        student
                                .getUser()
                                .getName()
                );
            }


            freelancer.setCollegeName(
                    student.getCollegeName()
            );

            freelancer.setCourse(
                    student.getCourse()
            );

            freelancer.setLocation(
                    student.getLocation()
            );

            freelancer.setHourlyRate(
                    student.getHourlyRate()
            );

            freelancer.setAvailabilityStatus(
                    student.getAvailabilityStatus()
            );

            freelancer.setVerificationStatus(
                    student.getVerificationStatus()
            );


            recommendedFreelancers.add(
                    freelancer
            );


            // Show maximum 5
            if (recommendedFreelancers.size()
                    >= 5) {

                break;
            }
        }


        response.setRecommendedFreelancers(
                recommendedFreelancers
        );


        // -------------------------------------------------
        // 12. TOTAL SPENT
        // -------------------------------------------------

        /*
         * There is currently no payment/transaction
         * table in the project.
         *
         * Therefore we should NOT pretend that a
         * project's budget is already spent.
         *
         * Until payment functionality is implemented,
         * dashboard totalSpent remains 0.
         */

        response.setTotalSpent(
                BigDecimal.ZERO
        );


        // -------------------------------------------------
        // 13. RETURN DASHBOARD
        // -------------------------------------------------

        return response;
    }
}