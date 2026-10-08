package com.freelance_platform.controller;
import com.freelance_platform.dto.ApplicationStatusRequest;
import com.freelance_platform.dto.ApplicationRequest;
import com.freelance_platform.entity.Application;
import com.freelance_platform.dto.ClientApplicationResponse;
import com.freelance_platform.service.ApplicationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }


    // ==========================================
    // STUDENT - APPLY
    // ==========================================

    @PostMapping
    public Application apply(
            @RequestBody ApplicationRequest request) {

        return applicationService.apply(request);
    }


    // ==========================================
    // STUDENT - MY APPLICATIONS
    // ==========================================

    @GetMapping("/student/{studentId}")
    public List<Application> getStudentApplications(
            @PathVariable Integer studentId) {

        return applicationService
                .getApplicationsByStudent(studentId);
    }


    // ==========================================
    // CLIENT - APPLICATIONS FOR ONE PROJECT
    // ==========================================

    @GetMapping("/gig/{gigId}")
    public List<Application> getGigApplications(
            @PathVariable Integer gigId) {

        return applicationService
                .getApplicationsByGig(gigId);
    }


    // ==========================================
    // CLIENT - ALL APPLICATIONS
    // ==========================================

    @GetMapping("/client/{userId}")
    public List<ClientApplicationResponse>
    getClientApplications(
            @PathVariable Integer userId) {

        return applicationService
                .getApplicationsByClient(userId);
    }
    
    @PutMapping("/{applicationId}/status")
    public Application updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestBody ApplicationStatusRequest request) {

        return applicationService.updateApplicationStatus(
                applicationId,
                request
        );
    }
}