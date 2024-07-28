package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.ReviewAppDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.entity.ReviewApp;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Role;
import com.espritech.workerExpress221.service.ReviewAppService;
import com.espritech.workerExpress221.service.ReviewWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews-app")
public class ReviewAppController {
    private ReviewAppService reviewAppService;

    @GetMapping("/{role}")
    public List<ReviewAppDto> findByRole(@PathVariable String role) {
        return  reviewAppService.findByRole(role);
    }

    @GetMapping("")
    public List<ReviewAppDto> findAll() {
        return  reviewAppService.findAll();
    }

    @GetMapping(value = "/isPublish")
    public List<ReviewAppDto> findAllByIsPublishTrue()
    {
        return reviewAppService.findAllByIsPublishTrue();
    }

    @PutMapping(value = "/{workerId}")
    public ReviewAppDto put(@PathVariable Long workerId, @RequestBody ReviewApp ReviewApp)
    {
        return  reviewAppService.put( workerId,ReviewApp );
    }
    @PostMapping("")
    public ReviewAppDto saveReviewApp(@RequestBody ReviewAppDto reviewAppDto
    ) throws IOException {
        return reviewAppService.saveReviewApp(reviewAppDto);
    }


    @GetMapping(value = "/isPublish/{id}")
    public String isPublish(@PathVariable Long id)
    {
        return reviewAppService.isPublish(id);
    }

}

