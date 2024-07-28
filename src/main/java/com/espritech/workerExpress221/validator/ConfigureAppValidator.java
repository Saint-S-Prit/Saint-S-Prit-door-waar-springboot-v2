package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.ConfigureAppDto;
import com.espritech.workerExpress221.dto.RegionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigureAppValidator {


    public static List<String> validate(ConfigureAppDto configureAppDto) {

        List<String> errors = new ArrayList<>();

        if (configureAppDto == null) {
            errors.add("Veuillez renseigner un paramName ");
            return errors;
        }


        if (!StringUtils.hasLength(configureAppDto.getParamName())) {
            errors.add("Veuillez renseigner un paramName ");
        }


        return errors;
    }


}
