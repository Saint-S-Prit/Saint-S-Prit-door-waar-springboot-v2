package com.espritech.workerExpress221.validator;

import com.espritech.workerExpress221.dto.WorkerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkerValidator {

    public static List<String> validate(WorkerDto workerDto) {

        List<String> errors = new ArrayList<>();
        if (workerDto == null) {

                errors.add("Veuillez renseigner le nom complet !");

                errors.add("Veuillez renseigner la profession !");

                errors.add("Veuillez renseigner la region !");

                errors.add("Veuillez renseigner le departement !");

                errors.add("Veuillez renseigner l'adresse !");

                errors.add("Veuillez renseigner le type de prestataire !");


                errors.add("Veuillez renseigner le profile !");

                errors.add("Veuillez renseigner une ou des realisation(s) !");

                errors.add("Veuillez renseigner le Numero Telephone !");

                errors.add("Numero de Telephone non valide !");

                errors.add("Veuillez renseigner votre photo de carte d'identite nationnale !");

                errors.add("Veuillez renseigner une description !");

                errors.add("La description trop longue: maximum 500 caracteres  !");



            return errors;
        }


        if (!StringUtils.hasLength(workerDto.getFullName())) {
            errors.add("Veuillez renseigner le nom complet !");
        }

        if (workerDto.getProfession() == null || workerDto.getProfession().getId() == null){
            errors.add("Veuillez renseigner le profession !");
        }


        if (workerDto.getRegion() == null || workerDto.getRegion().getId() == null){
            errors.add("Veuillez renseigner la region !");
        }


        if (workerDto.getDepartment() == null || workerDto.getDepartment().getId() == null){
            errors.add("Veuillez renseigner le departement !");
        }


        if (!StringUtils.hasLength(workerDto.getAddress())) {
            errors.add("Veuillez renseigner l'adresse !");
        }


        if (!StringUtils.hasLength(String.valueOf(workerDto.getProviderType()))) {
            errors.add("Veuillez renseigner le type de prestataire !");
        }



        if (workerDto.getAvatar() == null) {
            errors.add("Veuillez renseigner le profile !");
        }

        if (workerDto.getIllustrator() == null) {
            errors.add("Veuillez renseigner une ou des realisation(s) !");
        }

        // envoi de CNI
//        if (workerDto.getCni() == null) {
//            errors.add("Veuillez renseigner votre photo de carte d'identite nationnale !");
//        }


        if (!StringUtils.hasLength(workerDto.getPhoneNumber())) {
            errors.add("Veuillez renseigner le Numero Telephone !");
        }

        if (!workerDto.getPhoneNumber().matches("^\\+221(7[05-8])[0-9]{7}$")) {
            errors.add("Numero de telephone non valide !");
        }

        if (!StringUtils.hasLength(workerDto.getDescription())) {
            errors.add("Veuillez renseigner une description !");
        }

        if (workerDto.getDescription().length() > 500) {
            errors.add("La description trop longue: maximum 500 caracteres  !");
        }

        return errors;
    }



    public static List<String> validateUpdateInfos(WorkerDto workerDto) {

        List<String> errors = new ArrayList<>();
        if (workerDto == null) {

            errors.add("Veuillez renseigner le nom complet !");

            errors.add("Veuillez renseigner votre profession !");

            errors.add("Veuillez renseigner la region !");

            errors.add("Veuillez renseigner le departement !");

            errors.add("Veuillez renseigner l'adresse !");

            errors.add("Veuillez renseigner le type de profession !");

            errors.add("Veuillez renseigner le Numero Telephone !");

            errors.add("Numero de Telephone non valide !");

            errors.add("Veuillez renseigner une description !");

            errors.add("La description trop longue: maximum 500 caracteres  !");

            return errors;
        }


        if (!StringUtils.hasLength(workerDto.getFullName())) {
            errors.add("Veuillez renseigner le nom complet !");
        }


        if (workerDto.getRegion() == null || workerDto.getRegion().getId() == null){
            errors.add("Veuillez renseigner la region !");
        }


        if (workerDto.getDepartment() == null || workerDto.getDepartment().getId() == null){
            errors.add("Veuillez renseigner le departement !");
        }


        if (!StringUtils.hasLength(workerDto.getAddress())) {
            errors.add("Veuillez renseigner l'adresse !");
        }


        if (!StringUtils.hasLength(String.valueOf(workerDto.getProviderType()))) {
            errors.add("Veuillez renseigner le type de profession !");
        }

        if (!StringUtils.hasLength(String.valueOf(workerDto.getProfession()))) {
            errors.add("Veuillez renseigner votre profession !");
        }


        // envoi de CNI
//        if (workerDto.getCni() == null) {
//            errors.add("Veuillez renseigner votre photo de carte d'identite nationnale !");
//        }


        if (!StringUtils.hasLength(workerDto.getPhoneNumber())) {
            errors.add("Veuillez renseigner le Numero Telephone !");
        }

        if (!workerDto.getPhoneNumber().matches("^\\+221(7[05-8])[0-9]{7}$")) {
            errors.add("Numero de telephone non valide !");
        }

        if (!StringUtils.hasLength(workerDto.getDescription())) {
            errors.add("Veuillez renseigner une description !");
        }

        if (workerDto.getDescription().length() > 500) {
            errors.add("La description trop longue: maximum 500 caracteres  !");
        }

        return errors;
    }
}
