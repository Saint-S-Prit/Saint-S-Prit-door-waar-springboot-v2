package com.espritech.workerExpress221.dto;
import com.espritech.workerExpress221.entity.ConfigureApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class ConfigureAppDto {

    private Long id;
    private String paramName;
    private String paramValue;



    public static ConfigureAppDto fromEntity(ConfigureApp configureApp)
    {
        if (configureApp == null)
        {
            log.error("configureApp is null");
            return null;
        }

        return ConfigureAppDto.builder()
                .id(configureApp.getId())
                .paramName(configureApp.getParamName())
                .paramValue(configureApp.getParamValue())
                .build();
    }


    public static ConfigureApp toEntity(ConfigureAppDto configureAppDto)
    {
        if (configureAppDto == null)
        {
            log.error("configureAppDto is null");
            return null;
        }

        ConfigureApp configureApp = new ConfigureApp();
        configureApp.setId(configureAppDto.getId());
        configureApp.setParamName(configureAppDto.getParamName());
        configureApp.setParamValue(configureAppDto.getParamValue());

        return configureApp;

    }
}
