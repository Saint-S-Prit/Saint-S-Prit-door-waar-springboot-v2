package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.repository.RegionRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.RegionService;
import com.espritech.workerExpress221.validator.RegionValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;
    private ConfigureAppService configureAppService;


    @Override
    public RegionDto save(RegionDto regionDto) {

        List<String> errors = RegionValidator.validate(regionDto);
        if (!errors.isEmpty()) {
            log.error("Region is not valid {}", regionDto);
            throw new InvalidEntityException("La region n'est pas valide", ErrorCodes.REGION_NOT_VALID, errors);
        }

        regionRepository.findByNameAndArchiveFalse(regionDto.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La region " + regionDto.getName() + " existe déjà",
                            ErrorCodes.REGION_NOT_FOUND);
                });


        Region region = regionRepository.save(Objects.requireNonNull(RegionDto.toEntity(regionDto)));
        configureAppService.put("region");
        return RegionDto.fromEntity(region);

    }

    @Override
    public List<RegionDto> findAll() {
        return regionRepository.findAll().stream().map(RegionDto::fromEntity).toList();
    }

    @Override
    public List<RegionDto> findAllByArchiveFalse() {
        return regionRepository.findAllByArchiveFalse().stream().map(RegionDto::fromEntity).toList();
    }

    @Override
    public RegionDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Region region =  regionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Region avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );
        return RegionDto.fromEntity(region);
    }

    @Override
    public RegionDto put(Long id, Region region) {


        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        List<String> errors = RegionValidator.validate(RegionDto.fromEntity(region));
        if (!errors.isEmpty()) {
            log.error("Region is not valid {}", region);
            throw new InvalidEntityException("La region n'est pas valide", ErrorCodes.REGION_NOT_VALID, errors);
        }


        Region regionFound =  regionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Region avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );

        regionRepository.findByNameAndArchiveFalse(region.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La region " + region.getName() + " existe déjà",
                            ErrorCodes.REGION_NOT_FOUND);
                });

        regionFound.setName(region.getName());
        regionRepository.flush();
        configureAppService.put("region");
        return RegionDto.fromEntity(region);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }

        Region region =  regionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Region avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );

        region.setArchive(true);
        configureAppService.put("region");
        regionRepository.flush();

    }
}
