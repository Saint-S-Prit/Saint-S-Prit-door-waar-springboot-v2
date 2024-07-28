package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.SubProfessionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubProfessionValidator {

    public static List<String> validate(SubProfessionDto subProfessionDto) {

        List<String> errors = new ArrayList<>();

        if (subProfessionDto == null) {
            errors.add("Veuillez renseigner le nom de la sous-catégory profession !");
            return errors;
        }


        if (!StringUtils.hasLength(subProfessionDto.getName())) {
            errors.add("Veuillez renseigner le nom de la sous-catégory profession ! !");
        }

        return errors;
    }
}
