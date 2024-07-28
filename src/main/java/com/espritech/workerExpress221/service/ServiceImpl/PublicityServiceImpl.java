package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.PublicityDto;
import com.espritech.workerExpress221.entity.Publicity;
import com.espritech.workerExpress221.repository.PublicityRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.PublicityService;
import com.espritech.workerExpress221.validator.PublicityValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
    @AllArgsConstructor
    @Slf4j
    public class PublicityServiceImpl implements PublicityService {

        private PublicityRepository publicityRepository;
    private ConfigureAppService configureAppService;
        @Override
        public PublicityDto save(PublicityDto publicityDto) {

            List<String> errors = PublicityValidator.validate(publicityDto);
            if (!errors.isEmpty()) {
                log.error("Publicity is not valid {}", publicityDto);
                throw new InvalidEntityException("La publicité n'est pas valide", ErrorCodes.PUBLICITY_NOT_VALID, errors);
            }

            publicityRepository.findByNameAndArchiveFalse(publicityDto.getName().toLowerCase())
                    .ifPresent(existingProfession -> {
                        throw new EntityNotFoundException(
                                "La profession " + publicityDto.getName() + " existe déjà",
                                ErrorCodes.PUBLICITY_NOT_FOUND);
                    });

            Publicity publicity =  publicityRepository.save(Objects.requireNonNull(PublicityDto.toEntity(publicityDto)));
            configureAppService.put("publicity");
            return PublicityDto.fromEntity(publicity);
        }

        @Override
        public List<PublicityDto> findAll() {
            return publicityRepository.findAll().stream().map(PublicityDto::fromEntity).toList();
        }

        @Override
        public List<PublicityDto> findAllByArchiveFalse() {
            return publicityRepository.findAllByArchiveFalse().stream().map(PublicityDto::fromEntity).toList();
        }

        @Override
        public PublicityDto find(Long id) {
            if (id == null)
            {
                log.error("id is null");
                return null;
            }

            Publicity publicity =  publicityRepository.findByIdAndArchiveFalse(id).orElseThrow(
                    ()->
                            new EntityNotFoundException(
                                    "Publicity avec l'ID = " + id + " n'existe pas .",
                                    ErrorCodes.PUBLICITY_NOT_FOUND)
            );
            return PublicityDto.fromEntity(publicity);
        }

        @Override
        public PublicityDto put(Long id, Publicity publicity) {

//            List<String> errors = ProfessionValidator.validate(PublicityDto.fromEntity(publicity));
//            if (!errors.isEmpty()) {
//                log.error("Profession is not valid {}", publicity);
//                throw new InvalidEntityException("La profession n'est pas valide", ErrorCodes.PROFESSION_NOT_VALID, errors);
//            }
//            if (id == null)
//            {
//                log.error("id is null");
//                return null;
//            }

            Publicity publicityFound =  publicityRepository.findByIdAndArchiveFalse(id).orElseThrow(
                    ()->
                            new EntityNotFoundException(
                                    "Profession avec l'ID = " + id + " n'existe pas .",
                                    ErrorCodes.PUBLICITY_NOT_FOUND)
            );
            publicityRepository.findByNameAndArchiveFalse(publicity.getName().toLowerCase())
                    .ifPresent(existingProfession -> {
                        throw new EntityNotFoundException(
                                "La publicité " + publicity.getName() + " existe déjà",
                                ErrorCodes.PUBLICITY_NOT_FOUND);
                    });
            publicityFound.setName(publicity.getName());
            publicityRepository.flush();
            configureAppService.put("publicity");
            return PublicityDto.fromEntity(publicity);
        }

        @Override
        public void delete(Long id) {
            if (id == null)
            {
                log.error("id is null");
            }

            Publicity publicity =  publicityRepository.findByIdAndArchiveFalse(id).orElseThrow(
                    ()->
                            new EntityNotFoundException(
                                    "Publicity avec l'ID = " + id + " n'existe pas .",
                                    ErrorCodes.PUBLICITY_NOT_FOUND)
            );

            publicity.setArchive(true);
            configureAppService.put("publicity");
            publicityRepository.flush();

        }
    }

