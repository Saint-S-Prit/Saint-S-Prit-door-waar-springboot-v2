package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.Media;
import com.espritech.workerExpress221.entity.Worker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class MediaDto {

    private Long id;
    private String image;


    public static MediaDto fromEntity(Media media)
    {
        if (media == null)
        {
            log.error("media is null");
            return null;
        }

        return MediaDto.builder()
                .id(media.getId())
                .image(media.getImage())
                .build();
    }


    public static Media toEntity(MediaDto mediaDto)
    {
        if (mediaDto == null)
        {
            log.error("mediaDto is null");
            return null;
        }

        Media media = new Media();
        media.setId(mediaDto.getId());
        media.setImage(mediaDto.getImage());
        return media;

    }
}
