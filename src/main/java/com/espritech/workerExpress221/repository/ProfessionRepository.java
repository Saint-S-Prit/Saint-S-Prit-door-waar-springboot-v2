package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    List<Profession> findAllByArchiveFalse();

    Optional<Profession> findByIdAndArchiveFalse(Long id);

    Optional<Profession> findByNameAndArchiveFalse(String name);

    @Query("SELECT p FROM Profession p JOIN p.subProfessions sp WHERE sp.id = :id")
    Optional<Profession> findBySubProfessionId(@Param("id") Long id);
}
