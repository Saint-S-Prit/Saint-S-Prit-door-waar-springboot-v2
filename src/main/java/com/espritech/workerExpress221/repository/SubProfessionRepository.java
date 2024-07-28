package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.SubProfession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubProfessionRepository extends JpaRepository<SubProfession, Long> {
    List<SubProfession> findAllByArchiveFalse();

    Optional<SubProfession> findByIdAndArchiveFalse(Long id);

    Optional<SubProfession> findByNameAndArchiveFalse(String name);

}
