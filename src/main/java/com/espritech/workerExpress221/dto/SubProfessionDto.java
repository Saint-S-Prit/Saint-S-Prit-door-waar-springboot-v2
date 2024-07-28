package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.SubProfession;
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
public class SubProfessionDto {

    private Long id;
    private String name;
    private List<MediaDto> iconSubProfession;
    private ProfessionDto profession;



    public static SubProfessionDto fromEntity(SubProfession subProfession)
    {
        if (subProfession == null)
        {
            log.error("subProfession is null");
            return null;
        }

        return SubProfessionDto.builder()
                .id(subProfession.getId())
                .name(subProfession.getName())
                .iconSubProfession(
                        subProfession.getIconSubProfession() !=null?
                                subProfession.getIconSubProfession().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }


    public static SubProfession toEntity(SubProfessionDto subProfessionDto)
    {
        if (subProfessionDto == null)
        {
            log.error("subProfessionDto is null");
            return null;
        }

        SubProfession subProfession = new SubProfession();
        subProfession.setId(subProfessionDto.getId());
        subProfession.setName(subProfessionDto.getName());
        subProfession.setIconSubProfession(
                subProfessionDto.getIconSubProfession() !=null?
                        subProfessionDto.getIconSubProfession().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        subProfession.setProfession(ProfessionDto.toEntity(subProfessionDto.getProfession()));
        return subProfession;

    }
}
