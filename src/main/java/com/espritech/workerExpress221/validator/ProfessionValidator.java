package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.ProfessionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfessionValidator {

    public static List<String> validate(ProfessionDto professionDto) {

        List<String> errors = new ArrayList<>();

        if (professionDto == null) {
            errors.add("Veuillez renseigner une profession !");
            return errors;
        }


        if (!StringUtils.hasLength(professionDto.getName())) {
            errors.add("Veuillez renseigner une profession !");
        }

        return errors;
    }
}
