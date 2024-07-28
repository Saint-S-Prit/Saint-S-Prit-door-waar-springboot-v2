package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.dto.*;
import com.espritech.workerExpress221.entity.CallHistory;
import com.espritech.workerExpress221.entity.Role;
import com.espritech.workerExpress221.entity.TypeCall;
import com.espritech.workerExpress221.entity.UserApp;
import com.espritech.workerExpress221.repository.CallHistoryRepository;
import com.espritech.workerExpress221.repository.UserAppRepository;
import com.espritech.workerExpress221.repository.WorkerRepository;
import com.espritech.workerExpress221.service.CallHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CallHistoryServiceImpl implements CallHistoryService {

    private  CallHistoryRepository callHistoryRepository;
    private  UserAppRepository userAppRepository;
    private  WorkerRepository workerRepository;


    @Override
    public CallHistoryDto saveCallHistory(CallHistoryDto callHistoryDto) {

        if (callHistoryDto == null)
        {
            log.error("callHistoryDto is null");
            return null;
        }

       UserAppDto userAppDto = UserAppDto.fromEntity(userAppRepository.findById(callHistoryDto.getUserApp().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Ce user n'existe pas ",
                                ErrorCodes.WORKER_NOT_FOUND)
        ));
        WorkerDto workerDto = WorkerDto.fromEntity(workerRepository.findById(callHistoryDto.getWorker().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cet ouvrier n'existe pas ",
                                ErrorCodes.USER_NOT_FOUND)
        ));


        switch (callHistoryDto.getCallType().toString())
        {
            case "WHATSAPP" -> callHistoryDto.setCallType(TypeCall.WHATSAPP);
            default -> callHistoryDto.setCallType(TypeCall.CALL);
        }


        callHistoryDto.setWorker(workerDto);
        callHistoryDto.setUserApp(userAppDto);
        return CallHistoryDto.fromEntity(callHistoryRepository.save(Objects.requireNonNull(CallHistoryDto.toEntity(callHistoryDto))));

    }

    @Override
    public List<CallHistoryDto> getCallHistoryForUser(Long userId) {
        return callHistoryRepository.findByUserId(userId).stream().map(CallHistoryDto::fromEntity).toList();
    }
}
