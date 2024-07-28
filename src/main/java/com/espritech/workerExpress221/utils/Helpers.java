package com.espritech.workerExpress221.utils;

import com.espritech.workerExpress221.config.ConfigProperties;
import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.FileSizeLimitExceededException;
import com.espritech.workerExpress221.dto.MediaDto;
import com.espritech.workerExpress221.repository.MediaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component("Helpers")
@Service
@Configuration
@AllArgsConstructor
public class Helpers {

    final ConfigProperties configProperties;
    private MediaRepository mediaRepository;

    public List<String> saveFiles(MultipartFile[] images, String path) throws IOException {

        List<String> fileNames = new ArrayList<>();
        File folder = new File(path);


        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (MultipartFile image : images)
        {

            var imageFormatFileName = sanitize(String.valueOf(image.getOriginalFilename()));

            String[] allowedContentTypes = {"image/jpeg","image/jpg","image/png","image/gif","image/pdf", "image/JPEG","image/JPG","image/PNG","image/GIF","image/PDF"};
            List<String> allowedContentTypesList = Arrays.asList(allowedContentTypes);


            if (image.getSize() > configProperties.getFileSize()) {
                //System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
                //System.out.println(image.getSize());
                //long actualSize = image.getSize();
                //long permittedSize = 20971520; // 20 MB en bytes
                throw new FileSizeLimitExceededException(
                        "L'image " + image.getOriginalFilename() +
                                " depasse la taille maximale autorisee(10 mégaoctets (Mo))",
                        ErrorCodes.FILE_NOT_VALID
                );
            }

            if (!allowedContentTypesList.contains(Objects.requireNonNull(image.getContentType()).toLowerCase())) {
                throw new EntityNotFoundException("L'image " + image.getOriginalFilename() +
                        " doit etre au format jpeg, jpg, png, gif ou pdf", ErrorCodes.FILE_NOT_VALID);
            }

            String fileName = System.currentTimeMillis() + imageFormatFileName;
            String fullPath = path + File.separator + fileName;

            Files.copy(image.getInputStream(), Paths.get(fullPath));

            MediaDto mediaDto = new MediaDto();
            mediaDto.setImage(fileName);
            var media = MediaDto.fromEntity(mediaRepository.save(
                    Objects.requireNonNull(MediaDto.toEntity(mediaDto)))
            );
            assert media != null;
            fileNames.add(String.valueOf(media.getId()));
        }

        return fileNames;
    }

    //format number to add +221
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.startsWith("+221")) {
            phoneNumber = "+221" + phoneNumber;
        }
        return phoneNumber.replaceAll("\\s+", "");
    }

    public static String sanitize(String input)
    {
        // Remplacer les espaces par des tirets
        String sanitized = input.replaceAll("\\s+", "-");

        // Supprimer les accents
        sanitized = Normalizer.normalize(sanitized, Normalizer.Form.NFD);
        sanitized = sanitized.replaceAll("[^\\p{ASCII}]", "");

        // Retourner la chaîne sanitizée
        return sanitized;
    }
}



