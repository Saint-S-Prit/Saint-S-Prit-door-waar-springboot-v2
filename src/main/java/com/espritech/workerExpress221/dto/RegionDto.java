package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.Region;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class RegionDto {

    private Long id;
    private String name;
    private List<DepartmentDto> departments;


    public static RegionDto fromEntity(Region region)
    {
        if (region == null)
        {
            log.error("department is null");
            return null;
        }

        return RegionDto.builder()
                .id(region.getId())
                .name(region.getName())
                .departments(
                        region.getDepartments() !=null?
                                region.getDepartments().stream()
                                        .map(DepartmentDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }


    public static Region toEntity(RegionDto regionDto)
    {
        if (regionDto == null)
        {
            log.error("regionDto is null");
            return null;
        }

        Region region = new Region();
        region.setId(regionDto.getId());
        region.setName(regionDto.getName());

        return region;

    }



}
