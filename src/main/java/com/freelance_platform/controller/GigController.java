package com.freelance_platform.controller;

import com.freelance_platform.dto.CreateGigRequest;
import com.freelance_platform.entity.Gig;
import com.freelance_platform.service.GigService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gigs")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class GigController {

    private final GigService gigService;

    public GigController(GigService gigService) {
        this.gigService = gigService;
    }

    // GET ALL PROJECTS
    @GetMapping
    public List<Gig> getAllGigs() {
        return gigService.getAllGigs();
    }

    // GET OPEN PROJECTS
    @GetMapping("/open")
    public List<Gig> getOpenGigs() {
        return gigService.getOpenGigs();
    }

    // GET PROJECT BY ID
    @GetMapping("/{id}")
    public Gig getGig(@PathVariable Integer id) {
        return gigService.getGigById(id);
    }

    // CREATE PROJECT
    @PostMapping
    public Gig createGig(
            @RequestBody CreateGigRequest request) {

        return gigService.createGig(request);
    }

    // GET PROJECTS OF LOGGED-IN CLIENT
    @GetMapping("/client/{userId}")
    public List<Gig> getProjectsByClient(
            @PathVariable Integer userId) {

        return gigService.getProjectsByClient(userId);
    }
    
    @GetMapping("/student/{userId}")
    public List<Gig> getProjectsByStudent(
            @PathVariable Integer userId) {

        return gigService.getProjectsByStudent(userId);
    }
}