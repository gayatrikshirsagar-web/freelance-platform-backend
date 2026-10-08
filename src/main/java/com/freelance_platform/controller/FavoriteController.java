package com.freelance_platform.controller;

import com.freelance_platform.dto.FavoriteRequest;
import com.freelance_platform.entity.Favorite;
import com.freelance_platform.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public Favorite saveFavorite(
            @RequestBody FavoriteRequest request) {

        return favoriteService.saveFavorite(request);
    }

    @DeleteMapping("/{userId}/{gigId}")
    public void removeFavorite(
            @PathVariable Integer userId,
            @PathVariable Integer gigId) {

        favoriteService.removeFavorite(
                userId,
                gigId
        );
    }

    @GetMapping("/student/{userId}")
    public List<Favorite> getStudentFavorites(
            @PathVariable Integer userId) {

        return favoriteService.getStudentFavorites(
                userId
        );
    }

    @GetMapping("/check/{userId}/{gigId}")
    public boolean checkFavorite(
            @PathVariable Integer userId,
            @PathVariable Integer gigId) {

        return favoriteService.isFavorite(
                userId,
                gigId
        );
    }
}