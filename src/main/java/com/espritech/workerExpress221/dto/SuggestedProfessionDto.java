package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.entity.SuggestedProfession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class SuggestedProfessionDto {

    private Long id;
    private String name;



    public static SuggestedProfessionDto fromEntity(SuggestedProfession suggestedProfession)
    {
        if (suggestedProfession == null)
        {
            log.error("department is null");
            return null;
        }

        return SuggestedProfessionDto.builder()
                .id(suggestedProfession.getId())
                .name(suggestedProfession.getName())
                .build();

    }


    public static SuggestedProfession toEntity(SuggestedProfessionDto suggestedProfessionDto)
    {
        if (suggestedProfessionDto == null)
        {
            log.error("suggestedProfessionDto is null");
            return null;
        }

        SuggestedProfession suggestedProfession = new SuggestedProfession();
        suggestedProfession.setId(suggestedProfessionDto.getId());
        suggestedProfession.setName(suggestedProfessionDto.getName());

        return suggestedProfession;

    }
}
