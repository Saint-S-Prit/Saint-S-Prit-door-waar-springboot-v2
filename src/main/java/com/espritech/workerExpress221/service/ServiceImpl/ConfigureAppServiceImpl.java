package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.ConfigureAppDto;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.entity.ConfigureApp;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.repository.ConfigureAppRepository;
import com.espritech.workerExpress221.repository.RegionRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.validator.ConfigureAppValidator;
import com.espritech.workerExpress221.validator.RegionValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class ConfigureAppServiceImpl implements ConfigureAppService {
    private final ConfigureAppRepository configureAppRepository;

    @Override
    public ConfigureAppDto save(ConfigureAppDto configureAppDto) {

        List<String> errors = ConfigureAppValidator.validate(configureAppDto);
        if (!errors.isEmpty()) {
            log.error("configureAppDto is not valid {}", configureAppDto);
            throw new InvalidEntityException("La configureApp n'est pas valide", ErrorCodes.CONFIGURATION_APP_NOT_VALID, errors);
        }

        configureAppRepository.findByParamName(configureAppDto.getParamName().toLowerCase())
                .ifPresent(existingConfigureApp -> {
                    throw new EntityNotFoundException(
                            "La configureApp " + configureAppDto.getParamName() + " existe déjà",
                            ErrorCodes.CONFIGURATION_APP_NOT_FOUND);
                });
        configureAppDto.setParamValue(UUID.randomUUID().toString());

        return ConfigureAppDto.fromEntity(configureAppRepository.save(Objects.requireNonNull(ConfigureAppDto.toEntity(configureAppDto))));

    }

    @Override
    public List<ConfigureAppDto> findAll() {
        return configureAppRepository.findAll().stream().map(ConfigureAppDto::fromEntity).toList();

    }

    @Override
    public ConfigureAppDto findByParamName(String paramName) {
        if (paramName == null)
        {
            log.error("paramName is null");
            return null;
        }

        ConfigureApp configureApp =  configureAppRepository.findByParamName(paramName).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "ConfigureApp avec le paramName = " + paramName + " n'existe pas .",
                                ErrorCodes.CONFIGURATION_APP_NOT_FOUND)
        );
        return ConfigureAppDto.fromEntity(configureApp);
    }

    @Override
    public ConfigureAppDto put(String paramName) {


        if (paramName == null)
        {
            log.error("paramName is null");
            return null;
        }



        //List<String> errors = ConfigureAppValidator.validate(ConfigureAppDto.fromEntity(configureApp));
//        if (!errors.isEmpty()) {
//            log.error("ConfigureApp is not valid {}", configureApp);
//            throw new InvalidEntityException("La configureApp n'est pas valide", ErrorCodes.CONFIGURATION_APP_NOT_VALID, errors);
//        }


        ConfigureApp configureAppFound =  configureAppRepository.findByParamName(paramName).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "ConfigureApp avec le paramName = " + paramName + " n'existe pas .",
                                ErrorCodes.CONFIGURATION_APP_NOT_FOUND)
        );


        //configureAppFound.setParamName(configureApp.getParamName());
        configureAppFound.setParamValue(UUID.randomUUID().toString());
        configureAppRepository.flush();
        return ConfigureAppDto.fromEntity(configureAppFound);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }
        ConfigureApp configureAppFound =  configureAppRepository.findById(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "configureAppFound avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.CONFIGURATION_APP_NOT_FOUND)
        );

        configureAppFound.setArchive(true);
        configureAppRepository.flush();

    }
}
