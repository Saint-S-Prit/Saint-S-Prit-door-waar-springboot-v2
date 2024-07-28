package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.Worker;
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
public class ProfessionDto {

    private Long id;
    private String name;
    private List<MediaDto> iconProfession;
    private List<SubProfessionDto> subProfession;


    public static ProfessionDto fromEntity(Profession profession)
    {
        if (profession == null)
        {
            log.error("profession is null");
            return null;
        }

        return ProfessionDto.builder()
                .id(profession.getId())
                .name(profession.getName())
                .iconProfession(
                        profession.getIconProfession() !=null?
                                profession.getIconProfession().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .subProfession(
                        profession.getSubProfessions() !=null?
                                profession.getSubProfessions().stream()
                                        .map(SubProfessionDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }


    public static Profession toEntity(ProfessionDto professionDto)
    {
        if (professionDto == null)
        {
            log.error("professionDto is null");
            return null;
        }

        Profession profession = new Profession();
        profession.setId(professionDto.getId());
        profession.setName(professionDto.getName());
        profession.setIconProfession(
                professionDto.getIconProfession() !=null?
                        professionDto.getIconProfession().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        profession.setSubProfessions(
                professionDto.getSubProfession() !=null?
                        professionDto.getSubProfession().stream()
                                .map(SubProfessionDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        return profession;

    }
}


