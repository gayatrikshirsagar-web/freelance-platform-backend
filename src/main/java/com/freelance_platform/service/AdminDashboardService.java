package com.freelance_platform.service;

import com.freelance_platform.dto.AdminDashboardResponse;
import com.freelance_platform.entity.Application;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;
import com.freelance_platform.repository.ApplicationRepository;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.GigRepository;
import com.freelance_platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardService {

    private final StudentRepository studentRepository;
    private final ClientRepository clientRepository;
    private final GigRepository gigRepository;
    private final ApplicationRepository applicationRepository;

    public AdminDashboardService(
            StudentRepository studentRepository,
            ClientRepository clientRepository,
            GigRepository gigRepository,
            ApplicationRepository applicationRepository) {

        this.studentRepository = studentRepository;
        this.clientRepository = clientRepository;
        this.gigRepository = gigRepository;
        this.applicationRepository = applicationRepository;
    }

    public AdminDashboardResponse getDashboard() {

        AdminDashboardResponse response =
                new AdminDashboardResponse();

        List<Student> students =
                studentRepository.findAll();

        List<Client> clients =
                clientRepository.findAll();

        List<Gig> projects =
                gigRepository.findAll();

        List<Application> applications =
                applicationRepository.findAll();

        response.setTotalStudents(students.size());

        response.setTotalClients(clients.size());

        response.setTotalProjects(projects.size());

        response.setActiveProjects(
                projects.stream()
                        .filter(gig ->
                                "IN_PROGRESS".equals(gig.getStatus()))
                        .count()
        );

        response.setCompletedProjects(
                projects.stream()
                        .filter(gig ->
                                "COMPLETED".equals(gig.getStatus()))
                        .count()
        );

        response.setPendingApplications(
                applications.stream()
                        .filter(application ->
                                "PENDING".equals(
                                        application.getApplicationStatus()))
                        .count()
        );

        response.setPendingStudentVerifications(
                students.stream()
                        .filter(student ->
                                "PENDING".equals(
                                        student.getVerificationStatus()))
                        .count()
        );

        response.setPendingClientVerifications(
                clients.stream()
                        .filter(client ->
                                "PENDING".equals(
                                        client.getVerificationStatus()))
                        .count()
        );

        return response;
    }
}