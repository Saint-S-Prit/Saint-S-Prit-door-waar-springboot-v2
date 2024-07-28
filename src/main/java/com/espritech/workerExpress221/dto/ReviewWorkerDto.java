package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.ReviewWorker;
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
public class ReviewWorkerDto {
    private Long id;
    private UserAppDto client;
    private WorkerDto worker;
    private int rating; // Note (peut être un nombre d'étoiles, par exemple)
    private String comment; // Commentaire de l'avis
    private Instant createdAt;


    // Méthodes fromEntity et toEntity
    public static ReviewWorkerDto fromEntity(ReviewWorker reviewWorker) {
        if (reviewWorker == null) {
            return null;
        }

        return ReviewWorkerDto.builder()
                .id(reviewWorker.getId())
                .client(UserAppDto.fromEntity(reviewWorker.getClient()))
                //.worker(WorkerDto.fromEntity(reviewWorker.getWorker()))
                .rating(reviewWorker.getRating())
                .comment(reviewWorker.getComment())
                .createdAt(reviewWorker.getUpdatedAt())
                .build();
    }

    public static ReviewWorker toEntity(ReviewWorkerDto reviewWorkerDto) {
        if (reviewWorkerDto == null) {
            return null;
        }

        ReviewWorker reviewWorker = new ReviewWorker();
        reviewWorker.setId(reviewWorkerDto.getId());
        reviewWorker.setClient(UserAppDto.toEntity(reviewWorkerDto.getClient()));
        reviewWorker.setWorker(WorkerDto.toEntity(reviewWorkerDto.getWorker()));
        reviewWorker.setRating(reviewWorkerDto.getRating());
        reviewWorker.setComment(reviewWorkerDto.getComment());
        return reviewWorker;
    }
}
