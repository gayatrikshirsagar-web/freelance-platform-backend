package com.freelance_platform.service;

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
public class AdminService {

    private final StudentRepository studentRepository;
    private final ClientRepository clientRepository;
    private final GigRepository gigRepository;
    private final ApplicationRepository applicationRepository;

    public AdminService(
            StudentRepository studentRepository,
            ClientRepository clientRepository,
            GigRepository gigRepository,
            ApplicationRepository applicationRepository) {

        this.studentRepository = studentRepository;
        this.clientRepository = clientRepository;
        this.gigRepository = gigRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByStudentIdDesc();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAllByOrderByClientIdDesc();
    }

    public List<Gig> getAllProjects() {
        return gigRepository.findAllByOrderByGigIdDesc();
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAllByOrderByAppliedAtDesc();
    }

    public Student updateStudentVerification(
            Integer studentId,
            String status) {

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found"));

        student.setVerificationStatus(status);

        return studentRepository.save(student);
    }

    public Client updateClientVerification(
            Integer clientId,
            String status) {

        Client client =
                clientRepository.findById(clientId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Client not found"));

        client.setVerificationStatus(status);

        return clientRepository.save(client);
    }

    public Gig updateProjectStatus(
            Integer gigId,
            String status) {

        Gig gig =
                gigRepository.findById(gigId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found"));

        gig.setStatus(status);

        return gigRepository.save(gig);
    }

    public Application updateApplicationStatus(
            Integer applicationId,
            String status) {

        Application application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        application.setApplicationStatus(status);

        return applicationRepository.save(application);
    }
}