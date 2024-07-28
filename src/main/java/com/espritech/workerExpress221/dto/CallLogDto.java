package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.CallLog;
import com.espritech.workerExpress221.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class CallLogDto {

    private Long id;
    private String clientPhoneNumber;
    private String workerPhoneNumber;
    private String callType;
    private LocalDateTime callDateTime;
    private Instant createdAt;
    private Instant updatedAt;



    public static CallLogDto fromEntity(CallLog callLog)
    {
        if (callLog == null)
        {
            log.error("callLog is null");
            return null;
        }

        return CallLogDto.builder()
                .id(callLog.getId())
                .clientPhoneNumber(callLog.getClientPhoneNumber())
                .workerPhoneNumber(callLog.getWorkerPhoneNumber())
                .callType(callLog.getCallType())
                .callDateTime(callLog.getCallDateTime())
                .build();
    }


    public static CallLog toEntity(CallLogDto callLogDto)
    {
        if (callLogDto == null)
        {
            log.error("callLogDto is null");
            return null;
        }

        CallLog callLog = new CallLog();
        callLog.setId(callLogDto.getId());
        callLog.setClientPhoneNumber(callLogDto.getClientPhoneNumber());
        callLog.setWorkerPhoneNumber(callLogDto.getWorkerPhoneNumber());
        callLog.setCallType(callLogDto.getCallType());
        callLog.setCallDateTime(callLogDto.getCallDateTime());
        return callLog;

    }
}
