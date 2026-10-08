package com.freelance_platform.controller;

import com.freelance_platform.dto.StudentProfileResponse;
import com.freelance_platform.dto.StudentProfileUpdateRequest;
import com.freelance_platform.service.StudentProfileService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-profile")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;


    public StudentProfileController(
            StudentProfileService studentProfileService) {

        this.studentProfileService =
                studentProfileService;
    }


    // ==========================================
    // GET PROFILE
    // ==========================================

    @GetMapping("/{userId}")
    public StudentProfileResponse getProfile(
            @PathVariable Integer userId) {

        return studentProfileService
                .getProfile(userId);
    }


    // ==========================================
    // UPDATE PROFILE
    // ==========================================

    @PutMapping("/{userId}")
    public StudentProfileResponse updateProfile(
            @PathVariable Integer userId,
            @RequestBody StudentProfileUpdateRequest request) {

        return studentProfileService
                .updateProfile(
                        userId,
                        request
                );
    }
}