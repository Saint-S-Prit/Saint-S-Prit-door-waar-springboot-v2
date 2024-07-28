package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.dto.SuggestedProfessionDto;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.entity.SuggestedProfession;
import com.espritech.workerExpress221.repository.RegionRepository;
import com.espritech.workerExpress221.repository.SuggestedProfessionRepository;
import com.espritech.workerExpress221.service.SuggestedProfessionService;
import com.espritech.workerExpress221.validator.RegionValidator;
import com.espritech.workerExpress221.validator.SuggestedProfessionValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SuggestedProfessionServiceImpl implements SuggestedProfessionService {


    private SuggestedProfessionRepository suggestedProfessionRepository;

    @Override
    public SuggestedProfessionDto save(SuggestedProfessionDto suggestedProfessionDto) {

        List<String> errors = SuggestedProfessionValidator.validate(suggestedProfessionDto);
        if (!errors.isEmpty()) {
            log.error("Region is not valid {}", suggestedProfessionDto);
            throw new InvalidEntityException("La profession suggerer n'est pas valide", ErrorCodes.REGION_NOT_VALID, errors);
        }

        suggestedProfessionRepository.findByNameAndArchiveFalse(suggestedProfessionDto.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La region " + suggestedProfessionDto.getName() + " existe déjà",
                            ErrorCodes.REGION_NOT_FOUND);
                });
        return SuggestedProfessionDto.fromEntity(suggestedProfessionRepository.save(Objects.requireNonNull(SuggestedProfessionDto.toEntity(suggestedProfessionDto))));

    }

    @Override
    public List<SuggestedProfessionDto> findAll() {
        return suggestedProfessionRepository.findAll().stream().map(SuggestedProfessionDto::fromEntity).toList();
    }


    @Override
    public List<SuggestedProfessionDto> findAllByArchiveFalse() {
        return suggestedProfessionRepository.findAllByArchiveFalse().stream().map(SuggestedProfessionDto::fromEntity).toList();
    }

    @Override
    public SuggestedProfessionDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        SuggestedProfession suggestedProfession =  suggestedProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Region avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );
        return SuggestedProfessionDto.fromEntity(suggestedProfession);
    }

    @Override
    public SuggestedProfessionDto put(Long id, SuggestedProfession suggestedProfession) {



        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        List<String> errors = SuggestedProfessionValidator.validate(SuggestedProfessionDto.fromEntity(suggestedProfession));
        if (!errors.isEmpty()) {
            log.error("SuggestedProfession is not valid {}", suggestedProfession);
            throw new InvalidEntityException("La profession suggeréé n'est pas valide", ErrorCodes.REGION_NOT_VALID, errors);
        }


        SuggestedProfession suggestedProfessionFound =  suggestedProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Region avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );

        suggestedProfessionRepository.findByNameAndArchiveFalse(suggestedProfession.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La region " + suggestedProfession.getName() + " existe déjà",
                            ErrorCodes.REGION_NOT_FOUND);
                });

        suggestedProfessionFound.setName(suggestedProfession.getName());
        suggestedProfessionRepository.flush();
        return SuggestedProfessionDto.fromEntity(suggestedProfession);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }

        SuggestedProfession suggestedProfession =  suggestedProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "suggestedProfession avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );

        suggestedProfession.setArchive(true);
        suggestedProfessionRepository.flush();

    }



}
