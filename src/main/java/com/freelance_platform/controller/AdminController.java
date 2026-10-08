package com.freelance_platform.controller;

import com.freelance_platform.entity.Application;
import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.entity.Student;
import com.freelance_platform.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // STUDENTS

    @GetMapping("/students")
    public List<Student> getStudents() {
        return adminService.getAllStudents();
    }

    @PutMapping("/students/{studentId}/verification")
    public Student updateStudentVerification(
            @PathVariable Integer studentId,
            @RequestBody Map<String, String> request) {

        return adminService.updateStudentVerification(
                studentId,
                request.get("status")
        );
    }


    // CLIENTS

    @GetMapping("/clients")
    public List<Client> getClients() {
        return adminService.getAllClients();
    }

    @PutMapping("/clients/{clientId}/verification")
    public Client updateClientVerification(
            @PathVariable Integer clientId,
            @RequestBody Map<String, String> request) {

        return adminService.updateClientVerification(
                clientId,
                request.get("status")
        );
    }


    // PROJECTS

    @GetMapping("/projects")
    public List<Gig> getProjects() {
        return adminService.getAllProjects();
    }

    @PutMapping("/projects/{gigId}/status")
    public Gig updateProjectStatus(
            @PathVariable Integer gigId,
            @RequestBody Map<String, String> request) {

        return adminService.updateProjectStatus(
                gigId,
                request.get("status")
        );
    }


    // APPLICATIONS

    @GetMapping("/applications")
    public List<Application> getApplications() {
        return adminService.getAllApplications();
    }

    @PutMapping("/applications/{applicationId}/status")
    public Application updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestBody Map<String, String> request) {

        return adminService.updateApplicationStatus(
                applicationId,
                request.get("status")
        );
    }
}