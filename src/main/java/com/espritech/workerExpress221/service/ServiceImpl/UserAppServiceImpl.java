package com.espritech.workerExpress221.service.ServiceImpl;


import com.espritech.workerExpress221.auth.ChangePasswordRequest;
import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.entity.UserApp;
import com.espritech.workerExpress221.repository.UserAppRepository;
import com.espritech.workerExpress221.service.UserAppService;
import com.espritech.workerExpress221.entity.Role;
import com.espritech.workerExpress221.utils.Helpers;
import com.espritech.workerExpress221.validator.UserAppValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@AllArgsConstructor
public class UserAppServiceImpl implements UserAppService {

    PasswordEncoder passwordEncoder;
    UserAppRepository userAppRepository;
    private final Helpers helpers;

    @Override
    public UserAppDto save(UserAppDto userAppDto) {

        var phoneNumber = userAppDto.getPhoneNumber();
        if (!phoneNumber.startsWith("+221")) {
            userAppDto.setPhoneNumber("+221" + userAppDto.getPhoneNumber());
        }


        List<String> errors = UserAppValidator.validate(userAppDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", userAppDto);
            throw new InvalidEntityException("Le user n'est pas valide",  ErrorCodes.USER_NOT_VALID, errors);
        }
        userAppRepository.findByPhoneNumberAndArchiveFalse(userAppDto.getPhoneNumber())
                .ifPresent(existingPhoneNumber -> {
                    throw new EntityNotFoundException(
                            "Le  numero de telephone " + phoneNumber + " existe déjà",
                            ErrorCodes.USER_NOT_FOUND);
                });
        switch (userAppDto.getRole().name())
        {
            case "SUPER_ADMIN" -> userAppDto.setRole(Role.SUPER_ADMIN);
            case "ADMIN" -> userAppDto.setRole(Role.ADMIN);
            case "WORKER" -> userAppDto.setRole(Role.WORKER);
            case "AGENT" -> userAppDto.setRole(Role.AGENT);
            case "CUSTOMER" -> userAppDto.setRole(Role.CUSTOMER);
            default -> userAppDto.setRole(Role.CUSTOMER);
        }

        userAppDto.setPassword(passwordEncoder.encode(userAppDto.getPassword()));
        return UserAppDto.fromEntity(userAppRepository.save(Objects.requireNonNull(UserAppDto.toEntity(userAppDto))));
    }

    @Override
    public UserAppDto findById(Long id) {

        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        UserApp user =  userAppRepository.findById(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "User n'existe pas .",
                                ErrorCodes.USER_NOT_FOUND)
        );
        return UserAppDto.fromEntity(user);
    }


    @Override
    public UserAppDto findByPhoneNumber(String phoneNumber) {

        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }
        var phoneNumberFormat = helpers.formatPhoneNumber(phoneNumber);

        UserApp user =  userAppRepository.findByPhoneNumber(phoneNumberFormat).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "User avec le numero= " + phoneNumber + " n'existe pas .",
                                ErrorCodes.USER_NOT_FOUND)
        );
        return UserAppDto.fromEntity(user);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
        {
            log.error("phoneNumber is null");
        }

        assert id != null;
        UserApp user =  userAppRepository.findById(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "l'user  n'existe pas .",
                                ErrorCodes.USER_NOT_FOUND)
        );
        //user.setArchive(true); // Inverser la valeur si true
        userAppRepository.delete(user);
    }

    @Override
    public List<UserAppDto> findAll() {
        return userAppRepository.findAll().stream().map(UserAppDto::fromEntity).toList();
    }

    @Override
    public void delete(Long id) {

        if (id == null)
        {
            log.error("id is null");
            //return null;
        }
        //var phoneNumberFormat = helpers.formatPhoneNumber(phoneNumber);
        assert id != null;
        UserApp user =  userAppRepository.findById(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Ce user n'existe pas .",
                                ErrorCodes.USER_NOT_FOUND)
        );

        user.setArchive(true); // Inverser la valeur si true
        userAppRepository.flush();

    }

    @Override
    public void  changePassword(ChangePasswordRequest changePasswordRequest, Principal connectUser) {
        var user = (UserApp) ((UsernamePasswordAuthenticationToken) connectUser).getPrincipal();

        //check if the current password is correct
        if(!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
            log.error("User is not valid {}", changePasswordRequest);
            throw new InvalidEntityException("Le mot de passe  est incorrect",  ErrorCodes.USER_NOT_VALID);
        }

        //check if the new password and confirmation are the same
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())){
            log.error("User is not valid {}", changePasswordRequest);
            throw new InvalidEntityException("Les mots de passe  ne sont pas identiques",  ErrorCodes.USER_NOT_VALID);
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        userAppRepository.save(user);



    }
}
