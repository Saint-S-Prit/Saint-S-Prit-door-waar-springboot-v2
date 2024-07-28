package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.MediaDto;
import com.espritech.workerExpress221.repository.MediaRepository;
import com.espritech.workerExpress221.service.MediaService;
import com.espritech.workerExpress221.utils.Helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medias")
public class MediaController {

    MediaService mediaService;


    @PostMapping(value = "")
    public List<String> save(
            @RequestParam("image") MultipartFile[] images
    ) throws IOException{
        return mediaService.save(images);
    }
}


