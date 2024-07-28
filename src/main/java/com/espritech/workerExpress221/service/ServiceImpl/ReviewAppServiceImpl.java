package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.dto.ReviewAppDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.entity.ReviewApp;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Role;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.repository.ReviewAppRepository;
import com.espritech.workerExpress221.repository.ReviewWorkerRepository;
import com.espritech.workerExpress221.repository.UserAppRepository;
import com.espritech.workerExpress221.repository.WorkerRepository;
import com.espritech.workerExpress221.service.ReviewAppService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewAppServiceImpl implements ReviewAppService {

    private ReviewAppRepository reviewAppRepository;

    public List<ReviewAppDto> findAll() {

        return reviewAppRepository.findAll().stream().map(ReviewAppDto::fromEntity).toList();
    }

    @Override
    public List<ReviewAppDto> findAllByIsPublishTrue() {
        return reviewAppRepository.findAllByIsPublishTrueAndArchiveFalse()
                .stream()
                .map(ReviewAppDto::fromEntity).toList();
    }


    public List<ReviewAppDto> findByRole(String role) {

        return reviewAppRepository.findByRole(role).stream().map(ReviewAppDto::fromEntity).toList();
    }

    public ReviewAppDto saveReviewApp(ReviewAppDto reviewAppDto) {
        return ReviewAppDto.fromEntity(reviewAppRepository.save(Objects.requireNonNull(ReviewAppDto.toEntity(reviewAppDto))));

    }



    @Override
    public ReviewAppDto put(Long id, ReviewApp reviewApp) {

        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        ReviewApp reviewAppFound =  reviewAppRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Ce commentaire n'existe pas .",
                                ErrorCodes.REVIEW_APP_NOT_VALID)
        );

        reviewAppFound.setRating(reviewApp.getRating());
        reviewAppFound.setComment(reviewApp.getComment());
        reviewAppRepository.flush();
        return ReviewAppDto.fromEntity(reviewAppFound);


    }



    @Override
    public String isPublish(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        ReviewApp reviewAppFound =  reviewAppRepository.findById(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cette publication  n'existe pas .",
                                ErrorCodes.REVIEW_WORKER_NOT_FOUND)
        );

        if (reviewAppFound.getIsPublish()) {
            reviewAppFound.setIsPublish(false); // Inverser la valeur si true
            reviewAppRepository.flush();
            return "0";
        } else {
            reviewAppFound.setIsPublish(true); // Inverser la valeur si false
            reviewAppRepository.flush();
            return "1";
        }

    }
}
