package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping(value = "")
    public DepartmentDto save(@RequestBody DepartmentDto departmentDto
    ) throws IOException {


        return departmentService.save(departmentDto);
    }

    @GetMapping(value = "")
    public List<DepartmentDto> findAllByArchiveFalse()
    {
        return  departmentService.findAllByArchiveFalse();
    }

    @PutMapping(value = "/{id}")
    public DepartmentDto put(@PathVariable Long id, @RequestBody Department department)
    {
        return  departmentService.put( id,department );
    }
}
