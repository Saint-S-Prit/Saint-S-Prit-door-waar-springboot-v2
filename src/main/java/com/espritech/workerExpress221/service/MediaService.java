package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.MediaDto;
import com.espritech.workerExpress221.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    List<String> save(MultipartFile[] images) throws IOException;
    List<MediaDto> findAll();
    MediaDto find(Long id);
    MediaDto put(Long id, Media media);
    void delete(Long id);
}
