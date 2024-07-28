package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.dto.PublicityDto;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.Publicity;

import java.util.List;

public interface PublicityService {


    PublicityDto save(PublicityDto publicityDto);
    List<PublicityDto> findAll();
    List<PublicityDto> findAllByArchiveFalse();
    PublicityDto find(Long id);
    PublicityDto put(Long id, Publicity publicity);
    void delete(Long id);
}
