package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.dto.SubProfessionDto;
import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.SubProfession;

import java.util.List;

public interface SubProfessionService {


    SubProfessionDto save(SubProfessionDto subProfessionDto);
    List<SubProfessionDto> findAll();
    List<SubProfessionDto> findAllByArchiveFalse();
    SubProfessionDto find(Long id);
    SubProfessionDto put(Long id, SubProfession subProfession);
    void delete(Long id);
}
