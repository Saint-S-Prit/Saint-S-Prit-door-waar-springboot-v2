package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.CallLogDto;
import com.espritech.workerExpress221.entity.CallLog;
import com.espritech.workerExpress221.repository.CallLogRepository;
import com.espritech.workerExpress221.service.CallLogService;
import com.espritech.workerExpress221.utils.Helpers;
import com.espritech.workerExpress221.validator.CallLogValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CallLogServiceImpl implements CallLogService {

    private CallLogRepository callLogRepository;
    private final Helpers helpers;
    @Override
    public CallLogDto save(CallLogDto callLogDto) {


        var clientPhoneNumber = helpers.formatPhoneNumber(callLogDto.getClientPhoneNumber());
        callLogDto.setClientPhoneNumber(clientPhoneNumber);

        var workerPhoneNumber = helpers.formatPhoneNumber(callLogDto.getWorkerPhoneNumber());
        callLogDto.setWorkerPhoneNumber(workerPhoneNumber);


        List<String> errors = CallLogValidator.validate(callLogDto);
        if (!errors.isEmpty()) {
            log.error("CallLog is not valid {}", callLogDto);
            throw new InvalidEntityException("Le journal n'est pas valide", ErrorCodes.CALLLOG_NOT_VALID, errors);
        }


        return CallLogDto.fromEntity(callLogRepository.save(Objects.requireNonNull(CallLogDto.toEntity(callLogDto))));

    }

    @Override
    public CallLogDto findById(Long id) {
        return null;
    }

    @Override
    public List<CallLogDto> findByClientPhoneNumber(String clientPhoneNumber) {
        var clientPhoneNumber1 = helpers.formatPhoneNumber(clientPhoneNumber);
        if (clientPhoneNumber1 == null) {
            log.error("clientPhoneNumber is null");
            return Collections.emptyList(); // Retourne une liste vide en cas de clientPhoneNumber nul
        }

        List<CallLog> callLogs = callLogRepository.findByClientPhoneNumber(clientPhoneNumber1);

        if (!callLogs.isEmpty()) {
            return callLogs.stream().map(CallLogDto::fromEntity).toList();

        } else {
            throw new EntityNotFoundException(
                    "Aucun CallLog trouvé avec le numéro de client = " + clientPhoneNumber,
                    ErrorCodes.CALLLOG_NOT_FOUND
            );
        }
    }



    @Override
    public List<CallLogDto> findAll() {
        return callLogRepository.findAll().stream().map(CallLogDto::fromEntity).toList();

    }
}
