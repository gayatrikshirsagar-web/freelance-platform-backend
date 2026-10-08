package com.freelance_platform.service;

import com.freelance_platform.dto.CreateGigRequest;

import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;

import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.GigRepository;
import com.freelance_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GigService {

    private final GigRepository gigRepository;

    private final ClientRepository clientRepository;

    private final StudentRepository studentRepository;

    public GigService(
            GigRepository gigRepository,
            ClientRepository clientRepository,
            StudentRepository studentRepository) {

        this.gigRepository = gigRepository;

        this.clientRepository = clientRepository;

        this.studentRepository = studentRepository;
    }


    // =========================================================
    // GET ALL PROJECTS
    // =========================================================

    public List<Gig> getAllGigs() {

        return gigRepository.findAll();
    }


    // =========================================================
    // GET OPEN PROJECTS
    // =========================================================

    public List<Gig> getOpenGigs() {

        return gigRepository.findByStatus("OPEN");
    }


    // =========================================================
    // GET PROJECT BY ID
    // =========================================================

    public Gig getGigById(Integer id) {

        return gigRepository.findById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Gig not found with id: " + id
                        )
                );
    }


    // =========================================================
    // CREATE PROJECT
    // =========================================================

    public Gig createGig(CreateGigRequest request) {

        Client client = clientRepository

                .findByUserUserId(request.getUserId())

                .orElseThrow(() ->
                        new RuntimeException(
                                "Client profile not found for user: "
                                        + request.getUserId()
                        )
                );


        // Check budget

        if (request.getBudgetMin() != null

                && request.getBudgetMax() != null

                && request.getBudgetMin()
                        .compareTo(request.getBudgetMax()) > 0) {

            throw new RuntimeException(
                    "Minimum budget cannot be greater than maximum budget."
            );
        }


        Gig gig = new Gig();


        gig.setClientId(
                client.getClientId()
        );


        gig.setCategoryId(
                request.getCategoryId()
        );


        gig.setTitle(
                request.getTitle()
        );


        gig.setDescription(
                request.getDescription()
        );


        gig.setBudgetMin(
                request.getBudgetMin()
        );


        gig.setBudgetMax(
                request.getBudgetMax()
        );


        gig.setDeadline(
                request.getDeadline()
        );


        gig.setRequiredExperience(
                request.getRequiredExperience()
        );


        // New project is OPEN

        gig.setStatus("OPEN");


        return gigRepository.save(gig);
    }


    // =========================================================
    // GET PROJECTS POSTED BY CLIENT
    // =========================================================

    public List<Gig> getProjectsByClient(Integer userId) {

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


        return gigRepository.findByClientId(
                clientId
        );
    }


    // =========================================================
    // GET PROJECTS ASSIGNED TO STUDENT
    // =========================================================

    public List<Gig> getProjectsByStudent(Integer userId) {

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


        return gigRepository.findByAssignedStudentId(
                studentId
        );
    }


    // =========================================================
    // ASSIGN STUDENT TO PROJECT
    // =========================================================

    public Gig assignStudentToGig(
            Integer gigId,
            Integer studentId) {


        // Find project

        Gig gig = gigRepository

                .findById(gigId)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Gig not found with id: "
                                        + gigId
                        )
                );


        // Check if project is already assigned

        if (gig.getAssignedStudentId() != null) {

            throw new RuntimeException(
                    "This project already has a freelancer assigned."
            );
        }


        // Assign student

        gig.setAssignedStudentId(
                studentId
        );


        // Change project status

        gig.setStatus(
                "IN_PROGRESS"
        );


        // Save updated project

        return gigRepository.save(gig);
    }

}