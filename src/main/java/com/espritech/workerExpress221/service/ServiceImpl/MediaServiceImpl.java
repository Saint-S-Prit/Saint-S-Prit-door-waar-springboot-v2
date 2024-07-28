package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.ConfigProperties;
import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.dto.MediaDto;
import com.espritech.workerExpress221.entity.Media;
import com.espritech.workerExpress221.repository.MediaRepository;
import com.espritech.workerExpress221.service.MediaService;
import com.espritech.workerExpress221.utils.Helpers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private MediaRepository mediaRepository;
    private Helpers helpers;
    private ConfigProperties configProperties;

    @Override
    public List<String> save(MultipartFile[] images) throws IOException {
        //return helpers.saveFiles(images, "images/medias") ;
        return helpers.saveFiles(images, configProperties.getUpload());
    }

    @Override
    public List<MediaDto> findAll() {
        return mediaRepository.findAll().stream().map(MediaDto::fromEntity).toList();
    }

    @Override
    public MediaDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Media media =  mediaRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Media avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.MEDIA_NOT_FOUND)
        );

        return MediaDto.fromEntity(media);
    }

    @Override
    public MediaDto put(Long id, Media media) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }



        Media media1    =  mediaRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Media avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.MEDIA_NOT_FOUND)
        );

        media1.setImage(media.getImage());

        return MediaDto.fromEntity(media1);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }



        Media media1    =  mediaRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Media avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.MEDIA_NOT_FOUND)
        );

        media1.setArchive(true);
        mediaRepository.flush();
    }
}
