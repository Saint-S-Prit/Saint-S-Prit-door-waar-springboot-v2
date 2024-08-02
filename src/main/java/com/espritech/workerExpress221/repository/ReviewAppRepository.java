package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.*;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewAppRepository extends JpaRepository<ReviewApp, Long> {

    List<ReviewApp> findByRole(String role);

    Optional<ReviewApp> findByIdAndArchiveFalse(Long id);

    List<ReviewApp> findAllByIsPublishTrueAndArchiveFalse();

    void deleteAllByUserApp(UserApp user);

}
