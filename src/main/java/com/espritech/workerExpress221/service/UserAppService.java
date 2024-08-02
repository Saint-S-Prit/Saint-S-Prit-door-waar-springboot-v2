package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.auth.ChangePasswordRequest;
import com.espritech.workerExpress221.dto.UserAppDto;

import java.security.Principal;
import java.util.List;

public interface UserAppService {

    UserAppDto save(UserAppDto userAppDto);
    UserAppDto findById(Long id);
    UserAppDto findByPhoneNumber(String phoneNumber);
    void deleteById(Long id);
    List<UserAppDto> findAll();
    void delete(Long id);

    void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectUser);
    //UserDto findByEmail(String email);

}