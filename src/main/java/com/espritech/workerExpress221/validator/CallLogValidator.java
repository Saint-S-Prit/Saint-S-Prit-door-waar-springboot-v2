package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.CallLogDto;
import com.espritech.workerExpress221.dto.DepartmentDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CallLogValidator {
    public static List<String> validate(CallLogDto callLogDto) {

        List<String> errors = new ArrayList<>();

        if (callLogDto == null) {
                errors.add("Veuillez renseigner le numero du client !");
                errors.add("Numero de telephone du client  non valide !");
                errors.add("Veuillez renseigner le numero de l'ouvier !");
                errors.add("Numero de telephone de l'ouvrier non valide !");
                errors.add("Veuillez renseigner le type d'appel !");
                errors.add("Le type d'appel doit être WHATSAPP ou APPEL");
                return errors;
        }


        if (!StringUtils.hasLength(callLogDto.getWorkerPhoneNumber())) {
            errors.add("Veuillez renseigner le numero du client !");
        }

        if (!callLogDto.getWorkerPhoneNumber().matches("^\\+221(7[05-8])[0-9]{7}$")) {
            errors.add("Numero de telephone du client  non valide !");
        }

        if (!StringUtils.hasLength(callLogDto.getWorkerPhoneNumber())) {
            errors.add("Veuillez renseigner le numero de l'ouvier !");
        }

        if (!callLogDto.getWorkerPhoneNumber().matches("^\\+221(7[05-8])[0-9]{7}$")) {
            errors.add("Numero de telephone de l'ouvrier non valide !");
        }

        if (!StringUtils.hasLength(callLogDto.getCallType())) {
            errors.add("Veuillez renseigner le type d'appel !");
        }

        if (!callLogDto.getCallType().matches("WHATSAPP|APPEL")) {
            errors.add("Le type d'appel doit être WHATSAPP ou APPEL");
        }

        return errors;
    }
}
