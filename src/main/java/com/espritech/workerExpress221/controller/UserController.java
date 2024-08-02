package com.espritech.workerExpress221.controller;


import com.espritech.workerExpress221.auth.ChangePasswordRequest;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.service.UserAppService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    UserAppService userAppService;

    @PostMapping(value = "auth/register")
    public UserAppDto save(@RequestBody UserAppDto userAppDto) throws IOException {
        return userAppService.save(userAppDto);
    }

    @GetMapping("/users/{id}")
    public UserAppDto findById(@PathVariable Long id) {
        return userAppService.findById(id);
    }


    @GetMapping("/users/{findByPhoneNumber}/phone")
    public UserAppDto findByPhoneNumber(@PathVariable String findByPhoneNumber) {
        return userAppService.findByPhoneNumber(findByPhoneNumber);
    }


    @PatchMapping("/users/changePassword")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal connectUser) {
         userAppService.changePassword(changePasswordRequest, connectUser);
    }

    @GetMapping("/users")
    public List<UserAppDto> findAll() {
        return userAppService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userAppService.delete(id);
    }

    @DeleteMapping("/users/{phoneNumber}/delete")
    public void deleteByPhoneNumber(@PathVariable String phoneNumber) {
        userAppService.deleteByPhoneNumber(phoneNumber);
    }
}
