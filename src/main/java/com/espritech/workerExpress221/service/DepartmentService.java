package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.entity.Department;

import java.util.List;

public interface DepartmentService {


    DepartmentDto save(DepartmentDto departmentDto);
    List<DepartmentDto> findAll();
    List<DepartmentDto> findAllByArchiveFalse();
    DepartmentDto find(Long id);
    DepartmentDto put(Long id, Department department);
    void delete(Long id);
}
