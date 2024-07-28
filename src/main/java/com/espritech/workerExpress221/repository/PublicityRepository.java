package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.Publicity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PublicityRepository extends JpaRepository<Publicity, Long> {
    List<Publicity> findAllByArchiveFalse();

    Optional<Publicity> findByIdAndArchiveFalse(Long id);

    Optional<Publicity> findByNameAndArchiveFalse(String name);
}
