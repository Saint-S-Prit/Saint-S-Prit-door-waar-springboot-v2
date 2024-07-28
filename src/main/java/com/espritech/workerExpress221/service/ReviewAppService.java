package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.ReviewAppDto;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.entity.ReviewApp;
import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Role;

import java.util.List;

public interface ReviewAppService {

     List<ReviewAppDto> findAll();

     List<ReviewAppDto> findAllByIsPublishTrue();

     List<ReviewAppDto> findByRole(String role);

     ReviewAppDto saveReviewApp(ReviewAppDto reviewAppDto);

     ReviewAppDto put(Long id, ReviewApp reviewApp);

     String isPublish(Long id);


}
