package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.DepartmentDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DepartmentValidator {

    public static List<String> validate(DepartmentDto departmentDto) {

        List<String> errors = new ArrayList<>();

        if (departmentDto == null) {
            errors.add("Veuillez renseigner le departement !");
            errors.add("Veuillez renseigner la region !");
            return errors;
        }


        if (!StringUtils.hasLength(departmentDto.getName())) {
            errors.add("Veuillez renseigner le departement !");
        }

        if (departmentDto.getRegion() == null) {
            errors.add("Veuillez renseigner la region !");
        }

        return errors;
    }
}
