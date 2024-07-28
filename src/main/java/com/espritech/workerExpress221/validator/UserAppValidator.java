package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.dto.UserAppDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserAppValidator {

    public static List<String> validate(UserAppDto userAppDto) {

        List<String> errors = new ArrayList<>();

        if (userAppDto == null)
        {
            errors.add("Veuillez renseigner votre nom complet. ");
            errors.add("Veuillez renseigner votre numero de telephone. ");
            errors.add("Numero de telephone non valide !");
            errors.add("Veuillez renseigner un mot de passe");
            return errors;
        }


        if (!StringUtils.hasLength(userAppDto.getFullName()))
        {
            errors.add("Veuillez renseigner votre nom complet. ");
        }

        if (!StringUtils.hasLength(userAppDto.getPhoneNumber()))
        {
            errors.add("Veuillez renseigner votre numero de telephone. ");
        }

        if (!userAppDto.getPhoneNumber().matches("^\\+221(33|7[05-8])[0-9]{7}$"))
        {
            errors.add("Numero de telephone non valide !");
        }

        if (!StringUtils.hasLength(userAppDto.getPassword()))
        {
            errors.add("Veuillez renseigner un mot de passe");
        }

        return errors;
    }
}
