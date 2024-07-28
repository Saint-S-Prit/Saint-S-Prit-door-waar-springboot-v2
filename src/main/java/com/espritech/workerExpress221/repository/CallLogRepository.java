package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.CallLog;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
    Optional<CallLog> findAllByArchiveFalse();

    List<CallLog> findByClientPhoneNumber(String clientPhoneNumber);
}
