package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewAppRepository extends JpaRepository<ReviewApp, Long> {

    List<ReviewApp> findByRole(String role);

    Optional<ReviewApp> findByIdAndArchiveFalse(Long id);

    List<ReviewApp> findAllByIsPublishTrueAndArchiveFalse();

    @Transactional
    void deleteAllByUserApp(UserApp user);

}
