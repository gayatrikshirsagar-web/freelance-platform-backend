package com.freelance_platform.service;

import com.freelance_platform.entity.Notification;
import com.freelance_platform.repository.NotificationRepository;

import com.freelance_platform.dto.ApplicationStatusRequest;
import com.freelance_platform.dto.ApplicationRequest;
import com.freelance_platform.dto.ClientApplicationResponse;

import com.freelance_platform.entity.Application;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;

import com.freelance_platform.repository.ApplicationRepository;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.GigRepository;
import com.freelance_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final ClientRepository clientRepository;
    private final GigRepository gigRepository;
    private final NotificationRepository notificationRepository;


    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            ClientRepository clientRepository,
            GigRepository gigRepository,
            NotificationRepository notificationRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.clientRepository = clientRepository;
        this.gigRepository = gigRepository;
        this.notificationRepository = notificationRepository;
    }


    // ==========================================
    // STUDENT - APPLY FOR PROJECT
    // ==========================================

    public Application apply(ApplicationRequest request) {

        // 1. Find student using logged-in user ID
        Student student = studentRepository
                .findByUserUserId(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + request.getUserId()
                        )
                );

        Integer studentId = student.getStudentId();


        // 2. Check whether student already applied
        boolean alreadyApplied =
                applicationRepository.existsByGigIdAndStudentId(
                        request.getGigId(),
                        studentId
                );

        if (alreadyApplied) {

            throw new RuntimeException(
                    "You have already applied to this project."
            );
        }


        // 3. Create application
        Application application = new Application();

        application.setGigId(
                request.getGigId()
        );

        application.setStudentId(
                studentId
        );

        application.setCoverLetter(
                request.getCoverLetter()
        );

        application.setProposedPrice(
                request.getProposedPrice()
        );

        application.setEstimatedDays(
                request.getEstimatedDays()
        );

        application.setApplicationStatus(
                "PENDING"
        );


        // 4. Save application
        Application savedApplication =
                applicationRepository.save(application);


        // ==========================================
        // 5. CREATE NOTIFICATION FOR CLIENT
        // ==========================================

        // Find project
        Gig gig = gigRepository
                .findById(request.getGigId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: "
                                        + request.getGigId()
                        )
                );


        // Find client using client ID
        Client client = clientRepository
                .findById(gig.getClientId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Client not found with id: "
                                        + gig.getClientId()
                        )
                );


        // Create notification
        Notification notification = new Notification();

        notification.setUserId(
                client.getUser().getUserId()
        );

        notification.setTitle(
                "New Project Application"
        );

        notification.setMessage(
                "A student has applied for your project: "
                        + gig.getTitle()
        );

        notification.setNotificationType(
                "APPLICATION"
        );

        notification.setRelatedId(
                gig.getGigId()
        );


        // Save notification
        notificationRepository.save(notification);


        return savedApplication;
    }


    // ==========================================
    // STUDENT - VIEW OWN APPLICATIONS
    // ==========================================

    public List<Application> getApplicationsByStudent(
            Integer studentId) {

        return applicationRepository.findByStudentId(
                studentId
        );
    }


    // ==========================================
    // CLIENT - VIEW APPLICATIONS FOR ONE PROJECT
    // ==========================================

    public List<Application> getApplicationsByGig(
            Integer gigId) {

        return applicationRepository.findByGigId(
                gigId
        );
    }


    // ==========================================
    // CLIENT - VIEW ALL APPLICATIONS
    // FOR ALL THEIR PROJECTS
    // ==========================================

    public List<ClientApplicationResponse>
    getApplicationsByClient(Integer userId) {

        // 1. Find client using logged-in user ID
        Client client = clientRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Client profile not found for user: "
                                        + userId
                        )
                );


        Integer clientId =
                client.getClientId();


        // 2. Find all projects belonging to this client
        List<Gig> clientGigs =
                gigRepository.findByClientId(clientId);


        // 3. Create final response list
        List<ClientApplicationResponse> responses =
                new ArrayList<>();


        // 4. Get applications for every project
        for (Gig gig : clientGigs) {

            List<Application> applications =
                    applicationRepository.findByGigId(
                            gig.getGigId()
                    );


            // 5. Convert each application to response DTO
            for (Application application : applications) {

                ClientApplicationResponse response =
                        new ClientApplicationResponse();


                response.setApplicationId(
                        application.getApplicationId()
                );

                response.setGigId(
                        application.getGigId()
                );

                response.setProjectTitle(
                        gig.getTitle()
                );

                response.setStudentId(
                        application.getStudentId()
                );

                response.setCoverLetter(
                        application.getCoverLetter()
                );

                response.setProposedPrice(
                        application.getProposedPrice()
                );

                response.setEstimatedDays(
                        application.getEstimatedDays()
                );

                response.setApplicationStatus(
                        application.getApplicationStatus()
                );

                response.setAppliedAt(
                        application.getAppliedAt()
                );

                response.setReviewedAt(
                        application.getReviewedAt()
                );


                responses.add(response);
            }
        }


        return responses;
    }


    // ==========================================
    // CLIENT - ACCEPT / REJECT APPLICATION
    // ==========================================

    public Application updateApplicationStatus(
            Integer applicationId,
            ApplicationStatusRequest request) {


        // ==========================================
        // 1. VALIDATE STATUS
        // ==========================================

        if (!request.getStatus().equals("ACCEPTED")
                && !request.getStatus().equals("REJECTED")) {

            throw new RuntimeException(
                    "Invalid application status."
            );
        }


        // ==========================================
        // 2. FIND CLIENT USING USER ID
        // ==========================================

        Client client = clientRepository
                .findByUserUserId(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Client profile not found for user: "
                                        + request.getUserId()
                        )
                );


        // ==========================================
        // 3. FIND APPLICATION
        // ==========================================

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application not found with id: "
                                        + applicationId
                        )
                );


        // ==========================================
        // 4. FIND PROJECT / GIG
        // ==========================================

        Gig gig = gigRepository
                .findById(application.getGigId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found with id: "
                                        + application.getGigId()
                        )
                );


        // ==========================================
        // 5. VERIFY PROJECT BELONGS TO THIS CLIENT
        // ==========================================

        if (!gig.getClientId().equals(
                client.getClientId())) {

            throw new RuntimeException(
                    "You are not authorized to update this application."
            );
        }


        // ==========================================
        // 6. ACCEPT APPLICATION
        // ==========================================

        if (request.getStatus().equals("ACCEPTED")) {


            // Check whether another student is already assigned
            if (gig.getAssignedStudentId() != null) {

                throw new RuntimeException(
                        "This project already has a freelancer assigned."
                );
            }


            // Assign accepted student to project
            gig.setAssignedStudentId(
                    application.getStudentId()
            );


            // Change project status
            gig.setStatus(
                    "IN_PROGRESS"
            );


            // Save updated project
            gigRepository.save(gig);
        }


        // ==========================================
        // 7. UPDATE APPLICATION STATUS
        // ==========================================

        application.setApplicationStatus(
                request.getStatus()
        );

        application.setReviewedAt(
                LocalDateTime.now()
        );


        // ==========================================
        // 8. SAVE APPLICATION
        // ==========================================

        Application savedApplication =
                applicationRepository.save(application);


        // ==========================================
        // 9. FIND STUDENT
        // ==========================================

        Student student = studentRepository
                .findById(application.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: "
                                        + application.getStudentId()
                        )
                );


        // ==========================================
        // 10. CREATE NOTIFICATION FOR STUDENT
        // ==========================================

        Notification notification =
                new Notification();


        notification.setUserId(
                student.getUser().getUserId()
        );


        // ACCEPTED NOTIFICATION
        if (request.getStatus().equals("ACCEPTED")) {

            notification.setTitle(
                    "Application Accepted"
            );

            notification.setMessage(
                    "Your application for project '"
                            + gig.getTitle()
                            + "' has been accepted."
            );
        }


        // REJECTED NOTIFICATION
        else {

            notification.setTitle(
                    "Application Rejected"
            );

            notification.setMessage(
                    "Your application for project '"
                            + gig.getTitle()
                            + "' has been rejected."
            );
        }


        notification.setNotificationType(
                "APPLICATION"
        );

        notification.setRelatedId(
                gig.getGigId()
        );


        // Save notification
        notificationRepository.save(
                notification
        );


        return savedApplication;
    }
}