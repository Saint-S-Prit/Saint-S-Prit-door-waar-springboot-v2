package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.ReviewApp;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class ReviewAppDto {
    private Long id;
    private UserAppDto userApp;
    private String role;
    private int rating; // Note (peut être un nombre d'étoiles, par exemple)
    private String comment; // Commentaire de l'avis
    private Instant updatedAt;
    private Boolean isPublish = false;


    // Méthodes fromEntity et toEntity
    public static ReviewAppDto fromEntity(ReviewApp reviewApp) {
        if (reviewApp == null) {
            return null;
        }

        return ReviewAppDto.builder()
                .id(reviewApp.getId())
                .userApp(UserAppDto.fromEntity(reviewApp.getUserApp()))
                .role(reviewApp.getRole())
                .rating(reviewApp.getRating())
                .comment(reviewApp.getComment())
                .updatedAt(reviewApp.getUpdatedAt())
                .isPublish(reviewApp.getIsPublish())
                .build();
    }

    public static ReviewApp toEntity(ReviewAppDto reviewAppDto) {
        if (reviewAppDto == null) {
            return null;
        }

        ReviewApp reviewApp = new ReviewApp();
        reviewApp.setId(reviewAppDto.getId());
        reviewApp.setUserApp(UserAppDto.toEntity(reviewAppDto.getUserApp()));
        reviewApp.setRole(reviewAppDto.getRole());
        reviewApp.setRating(reviewAppDto.getRating());
        reviewApp.setComment(reviewAppDto.getComment());
        reviewApp.setIsPublish(reviewAppDto.getIsPublish());
        return reviewApp;
    }
}
