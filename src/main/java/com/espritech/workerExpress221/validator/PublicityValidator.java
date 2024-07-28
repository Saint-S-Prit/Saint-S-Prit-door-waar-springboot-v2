package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.PublicityDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PublicityValidator {

    public static List<String> validate(PublicityDto publicityDto) {

        List<String> errors = new ArrayList<>();

        if (publicityDto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise !");
            return errors;
        }


        if (!StringUtils.hasLength(publicityDto.getName())) {
            errors.add("Veuillez renseigner le nom de l'entreprise !");
        }

        return errors;
    }
}
