package com.freelance_platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "favorites",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_favorite_student_gig",
            columnNames = {"student_id", "gig_id"}
        )
    }
)
@Data
@IdClass(Favorite.FavoriteId.class)
public class Favorite {

    @Id
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Id
    @Column(name = "gig_id", nullable = false)
    private Integer gigId;

    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    @PrePersist
    protected void onCreate() {
        if (savedAt == null) {
            savedAt = LocalDateTime.now();
        }
    }

    // Composite primary key
    @Data
    public static class FavoriteId implements Serializable {

        private Integer studentId;
        private Integer gigId;
    }
}