package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.entity.Department;
import com.espritech.workerExpress221.repository.DepartmentRepository;
import com.espritech.workerExpress221.repository.RegionRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.DepartmentService;
import com.espritech.workerExpress221.validator.DepartmentValidator;
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
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;
    private RegionRepository regionRepository;
    private ConfigureAppService configureAppService;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {

        List<String> errors = DepartmentValidator.validate(departmentDto);
        if (!errors.isEmpty()) {
            log.error("Department is not valid {}", departmentDto);
            throw new InvalidEntityException("Le departement n'est pas valide", ErrorCodes.DEPARTMENT_NOT_VALID, errors);
        }

       regionRepository.findByIdAndArchiveFalse(departmentDto.getRegion().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cette region  n'existe pas .",
                                ErrorCodes.DEPARTMENT_NOT_FOUND)
        );


        departmentRepository.findByNameAndArchiveFalse(departmentDto.getName().toLowerCase())
                .ifPresent(existingDepartment -> {
                    throw new EntityNotFoundException(
                            "Le departement  " + departmentDto.getName() + " existe déjà",
                            ErrorCodes.DEPARTMENT_NOT_FOUND);
                });




        Department department = departmentRepository.save(Objects.requireNonNull(DepartmentDto.toEntity(departmentDto)));
        configureAppService.put("region");
        return DepartmentDto.fromEntity(department);


    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentDto::fromEntity).toList();
    }

    @Override
    public List<DepartmentDto> findAllByArchiveFalse() {
        return departmentRepository.findAllByArchiveFalse().stream().map(DepartmentDto::fromEntity).toList();
    }

    @Override
    public DepartmentDto find(Long id) {
        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Department department =  departmentRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Department avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.DEPARTMENT_NOT_FOUND)
        );
        return DepartmentDto.fromEntity(department);
    }

    @Override
    public DepartmentDto put(Long id, Department department) {

        List<String> errors = DepartmentValidator.validate(DepartmentDto.fromEntity(department));
        if (!errors.isEmpty()) {
            log.error("Department is not valid {}", department);
            throw new InvalidEntityException("Le departement n'est pas valide", ErrorCodes.DEPARTMENT_NOT_VALID, errors);
        }

        if (id == null)
        {
            log.error("id is null");
            return null;
        }

        Department departmentFound =  departmentRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Department avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.DEPARTMENT_NOT_FOUND)
        );

        departmentRepository.findByNameAndArchiveFalse(department.getName().toLowerCase())
                .ifPresent(existingDepartment -> {
                    throw new EntityNotFoundException(
                            "Le departement  " + department.getName() + " existe déjà",
                            ErrorCodes.DEPARTMENT_NOT_FOUND);
                });

        regionRepository.findByIdAndArchiveFalse(department.getRegion().getId()).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Cette region  n'existe pas .",
                                ErrorCodes.REGION_NOT_FOUND)
        );

        departmentFound.setName(department.getName());
        configureAppService.put("region");
        return DepartmentDto.fromEntity(departmentFound);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
        {
            log.error("id is null");
        }

        Department department =  departmentRepository.findByIdAndArchiveFalse(id).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Department avec l'ID = " + id + " n'existe pas .",
                                ErrorCodes.DEPARTMENT_NOT_FOUND)
        );



        department.setArchive(true);
        configureAppService.put("region");
        departmentRepository.flush();

    }
}
