package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.ConfigureAppDto;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.dto.WorkerDto;
import com.espritech.workerExpress221.entity.ConfigureApp;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.ProfessionService;
import com.espritech.workerExpress221.service.ServiceImpl.PaginationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/configure-app")
public class ConfigureAppController {

    private ConfigureAppService configureAppService;

    @PostMapping(value = "")
    public ConfigureAppDto save(@RequestBody ConfigureAppDto configureAppDto
    ) throws IOException {
        return configureAppService.save(configureAppDto);
    }

    @GetMapping(value = "")
    public List<ConfigureAppDto> findAll()
    {
        return  configureAppService.findAll();
    }


    @GetMapping(value = "/{paramName}")
    public ConfigureAppDto findByParamNam(@PathVariable String paramName)
    {
        return  configureAppService.findByParamName(paramName);
    }



    @PutMapping(value = "/{paramName}")
    public ConfigureAppDto  put(@PathVariable String paramName)
    {
        return  configureAppService.put(paramName);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id)
    {
        configureAppService.delete(id);
    }


}
