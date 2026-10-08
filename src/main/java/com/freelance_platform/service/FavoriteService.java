package com.freelance_platform.service;

import com.freelance_platform.dto.FavoriteRequest;
import com.freelance_platform.entity.Favorite;
import com.freelance_platform.entity.Student;
import com.freelance_platform.repository.FavoriteRepository;

import com.freelance_platform.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final StudentRepository studentRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            StudentRepository studentRepository) {

        this.favoriteRepository = favoriteRepository;
        this.studentRepository = studentRepository;
    }

    // Save project
    public Favorite saveFavorite(FavoriteRequest request) {

        Student student = studentRepository
                .findByUserUserId(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + request.getUserId()
                        )
                );

        Integer studentId = student.getStudentId();

        boolean alreadySaved =
                favoriteRepository.existsByStudentIdAndGigId(
                        studentId,
                        request.getGigId()
                );

        if (alreadySaved) {
            throw new RuntimeException(
                    "Project is already saved."
            );
        }

        Favorite favorite = new Favorite();

        favorite.setStudentId(studentId);
        favorite.setGigId(request.getGigId());

        return favoriteRepository.save(favorite);
    }

    // Remove saved project
    @Transactional
    public void removeFavorite(
            Integer userId,
            Integer gigId) {

        Student student = studentRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + userId
                        )
                );

        Integer studentId = student.getStudentId();

        favoriteRepository.deleteByStudentIdAndGigId(
                studentId,
                gigId
        );
    }

    // Get all favorites of a student
    public List<Favorite> getStudentFavorites(
            Integer userId) {

        Student student = studentRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + userId
                        )
                );

        return favoriteRepository.findByStudentId(
                student.getStudentId()
        );
    }

    // Check whether project is saved
    public boolean isFavorite(
            Integer userId,
            Integer gigId) {

        Student student = studentRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found for user: "
                                        + userId
                        )
                );

        return favoriteRepository.existsByStudentIdAndGigId(
                student.getStudentId(),
                gigId
        );
    }
}