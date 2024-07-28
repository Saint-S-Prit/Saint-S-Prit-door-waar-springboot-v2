package com.espritech.workerExpress221.dto;
import com.espritech.workerExpress221.entity.CallHistory;
import com.espritech.workerExpress221.entity.TypeCall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class CallHistoryDto {
    private Long id;
    private UserAppDto userApp;
    private WorkerDto worker;
    private TypeCall callType;
    private Instant createdAt;
    private Instant updatedAt;


    // Méthodes fromEntity et toEntity
    public static CallHistoryDto fromEntity(CallHistory callHistory) {
        if (callHistory == null) {
            return null;
        }

        return CallHistoryDto.builder()
                .userApp(UserAppDto.fromEntity(callHistory.getUser()))
                .worker(WorkerDto.fromEntity(callHistory.getWorker()))
                .callType(callHistory.getTypeCall())
                .createdAt(callHistory.getCreatedAt())
                .updatedAt(callHistory.getUpdatedAt())
                .build();
    }

    public static CallHistory toEntity(CallHistoryDto callHistoryDto) {
        if (callHistoryDto == null) {
            return null;
        }


        CallHistory callHistory = new CallHistory();
        callHistory.setId(callHistoryDto.getId());
        callHistory.setUser(UserAppDto.toEntity(callHistoryDto.getUserApp()));
        callHistory.setWorker(WorkerDto.toEntity(callHistoryDto.getWorker()));
        callHistory.setTypeCall(callHistoryDto.getCallType());
        return callHistory;
    }
}
