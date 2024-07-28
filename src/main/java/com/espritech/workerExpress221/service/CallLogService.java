package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.CallLogDto;

import java.util.List;

public interface CallLogService {

    CallLogDto save(CallLogDto callLogDto);
    CallLogDto findById(Long id);
    List<CallLogDto> findByClientPhoneNumber(String clientPhoneNumber);
    List<CallLogDto> findAll();
}
