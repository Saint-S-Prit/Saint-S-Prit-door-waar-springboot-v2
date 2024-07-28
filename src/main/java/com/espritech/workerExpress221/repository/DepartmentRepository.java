package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByArchiveFalse();

    Optional<Department> findByIdAndArchiveFalse(Long id);

    Optional<Department> findByNameAndArchiveFalse(String name);
}
