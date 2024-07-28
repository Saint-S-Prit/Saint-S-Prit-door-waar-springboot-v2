package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByIdAndArchiveFalse(Long id);

    List<Region> findAllByArchiveFalse();

    Optional<Region> findByNameAndArchiveFalse(String name);
}
