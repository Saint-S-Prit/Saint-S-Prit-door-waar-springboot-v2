package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.SuggestedProfession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SuggestedProfessionRepository extends JpaRepository<SuggestedProfession, Long> {
    Optional<SuggestedProfession> findByIdAndArchiveFalse(Long id);

    List<SuggestedProfession> findAllByArchiveFalse();

    Optional<SuggestedProfession> findByNameAndArchiveFalse(String name);
}
