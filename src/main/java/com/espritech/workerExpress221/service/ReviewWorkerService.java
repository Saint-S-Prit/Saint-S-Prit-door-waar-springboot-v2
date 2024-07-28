package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.entity.ReviewWorker;

import java.util.List;

public interface ReviewWorkerService {

     List<ReviewWorkerDto> getReviewsForWorker(Long workerId);
     ReviewWorkerDto saveReviewWorker(ReviewWorkerDto reviewWorkerDto);

     ReviewWorkerDto put(Long id, ReviewWorker reviewWorker);

}
