package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.repository.ReviewWorkerRepository;
import com.espritech.workerExpress221.repository.WorkerRepository;
import com.espritech.workerExpress221.service.ReviewWorkerService;
import com.espritech.workerExpress221.validator.RegionValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewWorkerServiceImpl implements ReviewWorkerService {

    private ReviewWorkerRepository reviewWorkerRepository;
    private WorkerRepository workerRepository;

    public List<ReviewWorkerDto> getReviewsForWorker(Long workerId) {

        var Worker =  workerRepository.findByIdAndArchiveFalse(workerId).orElseThrow(
                () -> new EntityNotFoundException(
                        "L'ouvrier n'existe pas",
                        ErrorCodes.WORKER_NOT_FOUND));


        return reviewWorkerRepository.findByWorker(Worker).stream().map(ReviewWorkerDto::fromEntity).toList();

    }

    public ReviewWorkerDto saveReviewWorker(ReviewWorkerDto reviewWorkerDto) {
        return ReviewWorkerDto.fromEntity(reviewWorkerRepository.save(Objects.requireNonNull(ReviewWorkerDto.toEntity(reviewWorkerDto))));

    }

    @Override
    public ReviewWorkerDto put(Long id, ReviewWorker reviewWorker) {

        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        ReviewWorker reviewWorkerFound =  reviewWorkerRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Ce commentaire n'existe pas .",
                                ErrorCodes.REVIEW_WORKER_NOT_VALID)
        );

        reviewWorkerFound.setRating(reviewWorker.getRating());
        reviewWorkerFound.setComment(reviewWorker.getComment());
        reviewWorkerRepository.flush();
        return ReviewWorkerDto.fromEntity(reviewWorkerFound);


    }
}
