package com.freelance_platform.controller;

import com.freelance_platform.dto.ClientProfileResponse;
import com.freelance_platform.dto.ClientProfileUpdateRequest;
import com.freelance_platform.service.ClientProfileService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client-profile")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class ClientProfileController {

    private final ClientProfileService clientProfileService;


    public ClientProfileController(
            ClientProfileService clientProfileService) {

        this.clientProfileService =
                clientProfileService;
    }


    // ============================
    // GET PROFILE
    // ============================

    @GetMapping("/{userId}")
    public ClientProfileResponse getProfile(
            @PathVariable Integer userId) {

        return clientProfileService
                .getProfile(userId);
    }


    // ============================
    // UPDATE PROFILE
    // ============================

    @PutMapping("/{userId}")
    public ClientProfileResponse updateProfile(
            @PathVariable Integer userId,
            @RequestBody ClientProfileUpdateRequest request) {

        return clientProfileService
                .updateProfile(
                        userId,
                        request
                );
    }
}