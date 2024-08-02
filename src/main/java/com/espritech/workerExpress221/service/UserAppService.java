package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.auth.ChangePasswordRequest;
import com.espritech.workerExpress221.dto.ReviewWorkerDto;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.entity.ReviewWorker;

import java.security.Principal;
import java.util.List;

public interface UserAppService {

    UserAppDto save(UserAppDto userAppDto);
    UserAppDto findById(Long id);
    UserAppDto findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    List<UserAppDto> findAll();
    void delete(Long id);

    void changePassword(ChangePasswordRequest changePasswordRequest, Principal connectUser);
    //UserDto findByEmail(String email);

}