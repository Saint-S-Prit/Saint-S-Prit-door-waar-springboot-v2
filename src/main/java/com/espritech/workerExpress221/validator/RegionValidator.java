package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.RegionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RegionValidator {

    public static List<String> validate(RegionDto regionDto) {

        List<String> errors = new ArrayList<>();

        if (regionDto == null) {
            errors.add("Veuillez renseigner une region ");
            return errors;
        }


        if (!StringUtils.hasLength(regionDto.getName())) {
            errors.add("Veuillez renseigner une region ");
        }

        return errors;
    }
}
