package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.CallHistory;
import com.espritech.workerExpress221.entity.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallHistoryRepository  extends JpaRepository<CallHistory, Long> {

    List<CallHistory> findByUserId(Long userId);
}
