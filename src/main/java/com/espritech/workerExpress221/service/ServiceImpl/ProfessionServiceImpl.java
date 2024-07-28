package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.dto.PublicityDto;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.entity.Publicity;
import com.espritech.workerExpress221.repository.ProfessionRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.ProfessionService;
import com.espritech.workerExpress221.validator.ProfessionValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
@Slf4j
public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionRepository professionRepository;
    private PaginationService paginationService;

    private ConfigureAppService configureAppService;


    @Override
    public ProfessionDto save(ProfessionDto professionDto) {

        List<String> errors = ProfessionValidator.validate(professionDto);
        if (!errors.isEmpty()) {
            log.error("Profession is not valid {}", professionDto);
            throw new InvalidEntityException("La profession n'est pas valide", ErrorCodes.PROFESSION_NOT_VALID, errors);
        }


        professionRepository.findByNameAndArchiveFalse(professionDto.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La profession " + professionDto.getName() + " existe déjà",
                            ErrorCodes.PROFESSION_NOT_FOUND);
                });





        Profession profession = professionRepository.save(
                Objects.requireNonNull(ProfessionDto.toEntity(professionDto))
        );
        configureAppService.put("profession");

        return ProfessionDto.fromEntity(profession);

    }


    public PaginationResponse<ProfessionDto> findAllWithPaginator(int pageNumber, int pageSize) {
        return paginationService.findAllWithPaginator(pageNumber, pageSize, professionRepository, ProfessionDto::fromEntity);
    }

    @Override
    public List<ProfessionDto> findAll() {
        return professionRepository.findAll().stream().map(ProfessionDto::fromEntity).toList();
    }

    @Override
    public List<ProfessionDto> findAllByArchiveFalse() {
        return professionRepository.findAllByArchiveFalse().stream().map(ProfessionDto::fromEntity).toList();
    }

    @Override
    public ProfessionDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Profession profession =  professionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Profession avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.PROFESSION_NOT_FOUND)
        );
        return ProfessionDto.fromEntity(profession);
    }

    @Override
    public ProfessionDto put(Long id, Profession profession) {

        List<String> errors = ProfessionValidator.validate(ProfessionDto.fromEntity(profession));
        if (!errors.isEmpty()) {
            log.error("Profession is not valid {}", profession);
            throw new InvalidEntityException("La profession n'est pas valide", ErrorCodes.PROFESSION_NOT_VALID, errors);
        }
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Profession professionFound =  professionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Profession avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.PROFESSION_NOT_FOUND)
        );
        professionRepository.findByNameAndArchiveFalse(profession.getName().toLowerCase())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "La profession " + profession.getName() + " existe déjà",
                            ErrorCodes.PROFESSION_NOT_FOUND);
                });
        professionFound.setName(profession.getName());
        professionRepository.flush();
        configureAppService.put("profession");
        return ProfessionDto.fromEntity(profession);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }

        Profession profession =  professionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Profession avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.PROFESSION_NOT_FOUND)
        );

        profession.setArchive(true);
        configureAppService.put("profession");
        professionRepository.flush();

    }
}
