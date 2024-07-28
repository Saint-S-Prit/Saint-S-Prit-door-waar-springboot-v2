package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.dto.SubProfessionDto;
import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.entity.SubProfession;
import com.espritech.workerExpress221.repository.DepartmentRepository;
import com.espritech.workerExpress221.repository.ProfessionRepository;
import com.espritech.workerExpress221.repository.RegionRepository;
import com.espritech.workerExpress221.repository.SubProfessionRepository;
import com.espritech.workerExpress221.service.DepartmentService;
import com.espritech.workerExpress221.service.SubProfessionService;
import com.espritech.workerExpress221.validator.DepartmentValidator;
import com.espritech.workerExpress221.validator.SubProfessionValidator;
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
public class SubProfessionServiceImpl implements SubProfessionService {


    private SubProfessionRepository subProfessionRepository;
    private ProfessionRepository professionRepository;

    @Override
    public SubProfessionDto save(SubProfessionDto subProfessionDto) {




        List<String> errors = SubProfessionValidator.validate(subProfessionDto);
        if (!errors.isEmpty()) {
            log.error("subProfession is not valid {}", subProfessionDto);
            throw new InvalidEntityException("La sous-catégory profession n'est pas valide", ErrorCodes.SUB_PROFESSION_NOT_VALID, errors);
        }

        System.out.println("profession");
        System.out.println(subProfessionDto.getProfession().getId());

       professionRepository.findByIdAndArchiveFalse(subProfessionDto.getProfession().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cette profession  n'existe pas .",
                                ErrorCodes.PROFESSION_NOT_FOUND)
        );


        subProfessionRepository.findByNameAndArchiveFalse(subProfessionDto.getName().toLowerCase())
                .ifPresent(existingDepartment -> {
                    throw new EntityNotFoundException(
                            "La sous-catégory profession  " + subProfessionDto.getName() + " existe déjà",
                            ErrorCodes.SUB_PROFESSION_FOUND);
                });



        return SubProfessionDto.fromEntity(subProfessionRepository.save(Objects.requireNonNull(SubProfessionDto.toEntity(subProfessionDto))));
    }

    @Override
    public List<SubProfessionDto> findAll() {
        return subProfessionRepository.findAll().stream().map(SubProfessionDto::fromEntity).toList();
    }

    @Override
    public List<SubProfessionDto> findAllByArchiveFalse() {
        return subProfessionRepository.findAllByArchiveFalse().stream().map(SubProfessionDto::fromEntity).toList();
    }

    @Override
    public SubProfessionDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        SubProfession subProfession =  subProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Department avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.DEPARTMENT_NOT_FOUND)
        );
        return SubProfessionDto.fromEntity(subProfession);
    }

    @Override
    public SubProfessionDto put(Long id, SubProfession subProfession) {

        List<String> errors = SubProfessionValidator.validate(SubProfessionDto.fromEntity(subProfession));
        if (!errors.isEmpty()) {
            log.error("SubProfession is not valid {}", subProfession);
            throw new InvalidEntityException("La sous-catégory profession n'est pas valide", ErrorCodes.SUB_PROFESSION_NOT_VALID, errors);
        }

        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        SubProfession subProfessionFound =  subProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "La sous-catégory profession avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.SUB_PROFESSION_NOT_FOUND)
        );

        subProfessionRepository.findByNameAndArchiveFalse(subProfession.getName().toLowerCase())
                .ifPresent(existingDepartment -> {
                    throw new EntityNotFoundException(
                            "Le departement  " + subProfession.getName() + " existe déjà",
                            ErrorCodes.DEPARTMENT_NOT_FOUND);
                });

        professionRepository.findByIdAndArchiveFalse(subProfession.getProfession().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cette profession  n'existe pas .",
                                ErrorCodes.PROFESSION_FOUND)
        );

        subProfessionFound.setName(subProfession.getName());
        return SubProfessionDto.fromEntity(subProfessionFound);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }

        SubProfession subProfession =  subProfessionRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "La sous-catégory avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.SUB_PROFESSION_NOT_FOUND)
        );

        subProfession.setArchive(true);
        subProfessionRepository.flush();

    }
}
