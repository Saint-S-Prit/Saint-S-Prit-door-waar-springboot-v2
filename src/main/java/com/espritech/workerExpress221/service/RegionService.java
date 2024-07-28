package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.dto.UserAppDto;
import com.espritech.workerExpress221.entity.Region;

import java.util.List;

public interface RegionService {


    RegionDto save(RegionDto regionDto);
    List<RegionDto> findAll();
    List<RegionDto> findAllByArchiveFalse();
    RegionDto find(Long id);
    RegionDto put(Long id, Region region);
    void delete(Long id);
}
