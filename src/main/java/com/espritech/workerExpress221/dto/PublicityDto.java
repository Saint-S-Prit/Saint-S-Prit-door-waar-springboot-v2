package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Publicity;
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
    public class PublicityDto {

        private Long id;
        private String name;
        private List<MediaDto> publicityImage;


        public static PublicityDto fromEntity(Publicity publicity)
        {
            if (publicity == null)
            {
                log.error("profession is null");
                return null;
            }

            return PublicityDto.builder()
                    .id(publicity.getId())
                    .name(publicity.getName())
                    .publicityImage(
                            publicity.getPublicityImage() !=null?
                                    publicity.getPublicityImage().stream()
                                            .map(MediaDto::fromEntity)
                                            .collect(Collectors.toList()) : null
                    )
                    .build();
        }


        public static Publicity toEntity(PublicityDto publicityDto)
        {
            if (publicityDto == null)
            {
                log.error("professionDto is null");
                return null;
            }

            Publicity publicity = new Publicity();
            publicity.setId(publicityDto.getId());
            publicity.setName(publicityDto.getName());
            publicity.setPublicityImage(
                    publicityDto.getPublicityImage() !=null?
                            publicityDto.getPublicityImage().stream()
                                    .map(MediaDto::toEntity)
                                    .collect(Collectors.toList()) : null
            );
            return publicity;

        }
    }
