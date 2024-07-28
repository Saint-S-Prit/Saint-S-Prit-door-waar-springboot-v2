package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.SuggestedProfessionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class SuggestedProfessionValidator {

    public static List<String> validate(SuggestedProfessionDto suggestedProfessionDto) {

        List<String> errors = new ArrayList<>();

        if (suggestedProfessionDto == null) {
            errors.add("Veuillez renseigner une profession ");
            return errors;
        }


        if (!StringUtils.hasLength(suggestedProfessionDto.getName())) {
            errors.add("Veuillez renseigner une profession ");
        }

        return errors;
    }
}
