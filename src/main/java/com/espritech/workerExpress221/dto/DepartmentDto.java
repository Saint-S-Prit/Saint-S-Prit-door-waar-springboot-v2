package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.Region;
import jakarta.persistence.ManyToOne;
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
public class DepartmentDto {

    private Long id;
    private String name;
    private RegionDto region;



    public static DepartmentDto fromEntity(Department department)
    {
        if (department == null)
        {
            log.error("department is null");
            return null;
        }

        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }


    public static Department toEntity(DepartmentDto departmentDto)
    {
        if (departmentDto == null)
        {
            log.error("departmentDto is null");
            return null;
        }

        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setRegion(RegionDto.toEntity(departmentDto.getRegion()));

        return department;

    }
}
