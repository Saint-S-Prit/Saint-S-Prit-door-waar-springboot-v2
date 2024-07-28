package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByIdAndArchiveFalse(Long id);
}
