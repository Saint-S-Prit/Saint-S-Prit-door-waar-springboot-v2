package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.entity.EmailSend;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmailValidation {

    public static List<String> validate(EmailSend emailSend) {

        List<String> errors = new ArrayList<>();

        if (emailSend == null) {
                errors.add("Veuillez renseigner votre email !");
                errors.add("Votre email n'es pas valide, Verifiez puis reessayez !");
                errors.add("Veuillez renseigner votre nom !");
                errors.add("Votre nom trop long !");
                errors.add("Veuillez renseigner votre message !");
                errors.add("Votre message est  trop long !");
                return errors;
        }

        if (!StringUtils.hasLength(emailSend.getSender())) {
            errors.add("Veuillez renseigner votre email !");
        }

        if (!emailSend.getSender().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            errors.add("Votre email n'es pas valide, Verifiez puis reessayez !");
        }

        if (!StringUtils.hasLength(emailSend.getSubject())) {
            errors.add("Veuillez renseigner votre nom !");
        }

        if (emailSend.getSubject().length() > 150) {
            errors.add("Votre nom trop long !");
        }


        if (!StringUtils.hasLength(emailSend.getMessage())) {
            errors.add("Veuillez renseigner votre message !");
        }

        if (emailSend.getMessage().length() > 500) {
            errors.add("Votre message est  trop long !");
        }

        return errors;
    }
}
