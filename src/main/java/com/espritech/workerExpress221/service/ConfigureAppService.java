package com.espritech.workerExpress221.service;


import com.espritech.workerExpress221.dto.ConfigureAppDto;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.ConfigureApp;
import com.espritech.workerExpress221.entity.Profession;

import java.util.List;

public interface ConfigureAppService {
    ConfigureAppDto save(ConfigureAppDto configureAppDto);
    //ConfigureAppDto findById(Long id);
    ConfigureAppDto findByParamName(String paramName);
    List<ConfigureAppDto> findAll();

    ConfigureAppDto put(String paramName);

    void delete(Long id);
}
