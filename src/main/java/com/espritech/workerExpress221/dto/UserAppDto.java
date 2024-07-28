package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Role;
import com.espritech.workerExpress221.entity.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserAppDto {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String password;
    private Role role;



    public static UserAppDto fromEntity(UserApp userApp)
    {
        if (userApp == null)
        {
            log.error("customer is null");
            return null;
        }

        return UserAppDto.builder()
                .id(userApp.getId())
                .fullName(userApp.getFullName())
                .phoneNumber(userApp.getPhoneNumber())
                .role(userApp.getRole())
                .build();
    }


    public static UserApp toEntity(UserAppDto userAppDto)
    {
        if (userAppDto == null)
        {
            log.error("userDto is null");
            return null;
        }

        UserApp userApp = new UserApp();
        userApp.setId(userAppDto.getId());
        userApp.setFullName(userAppDto.getFullName());
        userApp.setPhoneNumber(userAppDto.getPhoneNumber());
        userApp.setPassword(userAppDto.getPassword());
        userApp.setRole(userAppDto.getRole());

        return userApp;
    }

    
}
