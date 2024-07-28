package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.CallHistoryDto;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.dto.WorkerDto;

import java.util.List;

public interface CallHistoryService {

     CallHistoryDto saveCallHistory(CallHistoryDto callHistoryDto);
     List<CallHistoryDto> getCallHistoryForUser(Long userId);
}
