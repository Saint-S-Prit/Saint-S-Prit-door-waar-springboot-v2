package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.ReviewWorker;
import com.espritech.workerExpress221.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewWorkerRepository extends JpaRepository<ReviewWorker, Long> {
    List<ReviewWorker> findByWorker(Worker worker);

    Optional<ReviewWorker> findByIdAndArchiveFalse(Long id);
}