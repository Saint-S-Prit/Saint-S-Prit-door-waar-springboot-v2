package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.ConfigureApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigureAppRepository extends JpaRepository<ConfigureApp, Long> {
    Optional<ConfigureApp> findByParamName(String paramName);
}
