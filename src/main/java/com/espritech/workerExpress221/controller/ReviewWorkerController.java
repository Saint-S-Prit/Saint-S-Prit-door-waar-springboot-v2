package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.service.ReviewWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews-worker")
public class ReviewWorkerController {

    private ReviewWorkerService reviewWorkerService;

    @GetMapping("/{workerId}")
    public List<ReviewWorkerDto> getReviewsForWorkerId(@PathVariable Long workerId) {
        return  reviewWorkerService.getReviewsForWorker(workerId);
    }

    @PutMapping(value = "/{workerId}")
    public ReviewWorkerDto put(@PathVariable Long workerId, @RequestBody ReviewWorker reviewWorker)
    {
        return  reviewWorkerService.put( workerId,reviewWorker );
    }

    @PostMapping("")
    public ReviewWorkerDto saveReviewWorker(@RequestBody ReviewWorkerDto reviewWorkerDto
    ) throws IOException {
        return reviewWorkerService.saveReviewWorker(reviewWorkerDto);
    }
}

