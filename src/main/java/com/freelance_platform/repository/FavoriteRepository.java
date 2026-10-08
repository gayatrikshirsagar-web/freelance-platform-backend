package com.freelance_platform.repository;

import com.freelance_platform.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Favorite.FavoriteId> {

    List<Favorite> findByStudentId(Integer studentId);

    boolean existsByStudentIdAndGigId(
            Integer studentId,
            Integer gigId
    );

    void deleteByStudentIdAndGigId(
            Integer studentId,
            Integer gigId
    );
}