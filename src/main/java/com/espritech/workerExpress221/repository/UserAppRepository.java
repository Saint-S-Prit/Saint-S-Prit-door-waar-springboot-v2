package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByPhoneNumber(String phoneNumber);
}
