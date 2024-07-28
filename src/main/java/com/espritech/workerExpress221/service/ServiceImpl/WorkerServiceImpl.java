package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.EntityNotFoundException;
import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.WorkerDto;
import com.espritech.workerExpress221.entity.Media;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.repository.MediaRepository;
import com.espritech.workerExpress221.repository.ProfessionRepository;
import com.espritech.workerExpress221.repository.WorkerRepository;
import com.espritech.workerExpress221.service.ConfigureAppService;
import com.espritech.workerExpress221.service.WorkerService;
import com.espritech.workerExpress221.utils.Helpers;
import com.espritech.workerExpress221.validator.WorkerValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class WorkerServiceImpl implements WorkerService {

     WorkerRepository workerRepository;
     ProfessionRepository professionRepository;
     MediaRepository mediaRepository;

    private ConfigureAppService configureAppService;

    private PaginationService paginationService;
    private final Helpers helpers;


    @Override
    public PaginationResponse<WorkerDto> findAllByFullNameAndIsWorkerTrueWithPaginator(
            String fullName, int pageNumber, int pageSize) {
        return paginationService.findAllByFullNameAndIsWorkerTrueWithPaginator(fullName, pageNumber, pageSize, workerRepository);
    }

    @Override
    public WorkerDto save(WorkerDto workerDto) {


        var phoneNumber = helpers.formatPhoneNumber(workerDto.getPhoneNumber());
        workerDto.setPhoneNumber(phoneNumber);

        List<String> errors = WorkerValidator.validate(workerDto);
        if (!errors.isEmpty()) {
            log.error("Worker is not valid {}", workerDto);
            throw new InvalidEntityException("L'ouvrier n'est pas valide", ErrorCodes.WORKER_NOT_VALID, errors);
        }

        workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(workerDto.getPhoneNumber())
                .ifPresent(existingProfession -> {
                    throw new EntityNotFoundException(
                            "Le numero " + workerDto.getPhoneNumber() + " existe déjà",
                            ErrorCodes.WORKER_NOT_FOUND);
                });

        if (workerDto.getSubProfession() != null)
        {

            professionRepository.findBySubProfessionId(workerDto.getSubProfession().getId()).orElseThrow(
                    ()-> new EntityNotFoundException(
                            "Cette catégory n'existe pas dans cette profession ",
                            ErrorCodes.SUB_PROFESSION_NOT_FOUND)
            );
        }

        return WorkerDto.fromEntity(workerRepository.save(Objects.requireNonNull(WorkerDto.toEntity(workerDto))));
    }

    @Override
    public List<WorkerDto> findAll() {
        return workerRepository.findAll().stream().map(WorkerDto::fromEntity).toList();
    }



    public PaginationResponse<WorkerDto> findAllWithPaginator(int pageNumber, int pageSize) {
        return paginationService.findAllWithPaginator(pageNumber, pageSize, workerRepository, WorkerDto::fromEntity);
    }


    @Override
    public Page<Worker> findAllWithPaginatorAndSort(int pageNumber, int pageSize, String field) {
        return workerRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

    @Override
    public List<WorkerDto> findAllByArchiveFalseAndIsWorkerTrue() {
        return workerRepository.findAllByArchiveFalseAndIsWorkerTrue().stream().map(WorkerDto::fromEntity).toList();
    }

    @Override
    public List<WorkerDto> findAllByArchiveFalseAndIsWorkerFalse() {
        return workerRepository.findAllByArchiveFalseAndIsWorkerFalse().stream().map(WorkerDto::fromEntity).toList();

    }

    @Override
    public List<WorkerDto> findAllByRecommendTrueAndIsWorkerTrue() {
        return workerRepository.findAllByRecommendTrueAndIsWorkerTrue().stream().map(WorkerDto::fromEntity).toList();
    }

    @Override
    public List<WorkerDto> findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(String profession, String region, String department) {
        return workerRepository.findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(profession,region,department ).stream().map(WorkerDto::fromEntity).toList();
    }


    @Override
    public WorkerDto findByPhoneNumber(String phoneNumber) {

        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }

        var phoneNumberFound = helpers.formatPhoneNumber(phoneNumber);


        Worker worker =  workerRepository.findByPhoneNumberAndArchiveFalse(phoneNumberFound).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );


        return WorkerDto.fromEntity(worker);
    }

    @Override
    public WorkerDto put(String phoneNumber, Worker worker) {


        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }


        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);
        worker.setPhoneNumber(phoneNumber1);

        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );


//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserApp userDetails = (UserApp) authentication.getPrincipal();
//
//        if (!phoneNumber1.equals(userDetails.getPhoneNumber()))
//        {
//            throw  new EntityNotFoundException(
//                    "Vous n etes pas autorise a fournir ce service.",
//                    ErrorCodes.NOT_AUTHORIZE);
//        }


        List<String> errors = WorkerValidator.validateUpdateInfos(WorkerDto.fromEntity(worker));
        if (!errors.isEmpty()) {
            log.error("Worker is not valid {}", worker);
            throw new InvalidEntityException("L'ouvrier n'est pas valide", ErrorCodes.WORKER_NOT_VALID, errors);
        }

        workerFound.setFullName(worker.getFullName());
        workerFound.setPhoneNumber(worker.getPhoneNumber());
        workerFound.setProfession(worker.getProfession());
        workerFound.setSubProfession(worker.getSubProfession());
        workerFound.setProviderType(worker.getProviderType());
        workerFound.setRegion(worker.getRegion());
        workerFound.setDepartment(worker.getDepartment());
        workerFound.setAddress(worker.getAddress());
        workerFound.setDescription(worker.getDescription());
        workerRepository.flush();
        return WorkerDto.fromEntity(workerFound);
    }

    @Override
    public WorkerDto editWorkerAddCni(String phoneNumber, Long cni) {
        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }


        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);


        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );


        Media mediaFound = mediaRepository.findById(cni)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ce cni n'existe pas",
                        ErrorCodes.CNI_NOT_FOUND));

        List<Media> cniList = new ArrayList<>();
        cniList.add(mediaFound);

        workerFound.setCni(cniList);
        workerRepository.flush();
        return WorkerDto.fromEntity(workerFound);
    }

    @Override
    public WorkerDto updateAvatar(String phoneNumber, Long avatar) {
        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }

        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);


        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );


        Media mediaFound = mediaRepository.findById(avatar)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ce cni n'existe pas",
                        ErrorCodes.CNI_NOT_FOUND));

        List<Media> avatarList = new ArrayList<>();
        avatarList.add(mediaFound);
        workerFound.setAvatar(avatarList);
        workerRepository.flush();
        configureAppService.put("recommend");
        return WorkerDto.fromEntity(workerFound);
    }



    @Override
    public WorkerDto updateCertificate(String phoneNumber, Long certificate) {
        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }

        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);


        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );


        Media mediaFound = mediaRepository.findById(certificate)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ce certicate ou diplome n'existe pas",
                        ErrorCodes.CERTIFICATE_NOT_FOUND));

        List<Media> certificateList = new ArrayList<>();
        certificateList.add(mediaFound);
        workerFound.setCertificate(certificateList);
        workerRepository.flush();
        configureAppService.put("recommend");
        return WorkerDto.fromEntity(workerFound);
    }


    @Override
    public WorkerDto updateIllustrations(String phoneNumber, List<Media> newIllustrations) {
        // Récupérer l'ouvrier
        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);

        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );

        // Récupérer les illustrations actuelles de l'ouvrier
        List<Media> currentIllustrations = workerFound.getIllustrator();

        // Supprimer les illustrations qui ne sont pas présentes dans la liste des nouvelles illustrations
        currentIllustrations.removeIf(illustration -> !newIllustrations.contains(illustration));

        // Ajouter les nouvelles illustrations à l'ouvrier
        currentIllustrations.addAll(newIllustrations);

        // Mettre à jour l'ouvrier dans la base de données
        workerRepository.save(workerFound);
        configureAppService.put("recommend");
        // Retourner une représentation DTO de l'ouvrier mis à jour
        return WorkerDto.fromEntity(workerFound);
    }



    @Override
    public String isWorker(String phoneNumber) {
        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }
        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);

        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalse(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );

        if (workerFound.getIsWorker()) {
            workerFound.setIsWorker(false); // Inverser la valeur si true
            workerRepository.flush();
            configureAppService.put("recommend");

            return "0";
        } else {
            workerFound.setIsWorker(true); // Inverser la valeur si false
            workerRepository.flush();
            configureAppService.put("recommend");
            return "1";
        }

    }

    @Override
    public String isAvailable(String phoneNumber) {
        if (phoneNumber == null) {
            log.error("phoneNumber is null");
            return null;
        }

        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);

        Worker workerFound = workerRepository.findByPhoneNumberAndArchiveFalse(phoneNumber1).orElseThrow(
                () -> new EntityNotFoundException(
                        "L'ouvrier avec le numéro = " + phoneNumber + " n'existe pas.",
                        ErrorCodes.WORKER_NOT_FOUND)
        );

        if (workerFound.getIsAvailable()) {
            workerFound.setIsAvailable(false); // Inverser la valeur si true
            workerRepository.flush();
            configureAppService.put("recommend");
            return "0";
        } else {
            workerFound.setIsAvailable(true); // Inverser la valeur si false
            workerRepository.flush();
            configureAppService.put("recommend");
            return "1";
        }
    }

    @Override
    public String isRecommendWorker(String phoneNumber) {
        if (phoneNumber == null)
        {
            log.error("phoneNumber is null");
            return null;
        }
        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);

        Worker workerFound =  workerRepository.findByPhoneNumberAndArchiveFalse(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "L'ouvrier avec le numero = " + phoneNumber + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );

        if (workerFound.getRecommend()) {
            workerFound.setRecommend(false); // Inverser la valeur si true
            workerRepository.flush();
            configureAppService.put("recommend");
            return "0";
        } else {
            workerFound.setRecommend(true); // Inverser la valeur si false
            workerRepository.flush();
            configureAppService.put("recommend");
            return "1";
        }
    }

    @Override
    public void delete(String phoneNumber) {
        if (phoneNumber == null)
        {
            log.error("id is null");
        }

        var phoneNumber1 = helpers.formatPhoneNumber(phoneNumber);


         Worker worker =  workerRepository.findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(phoneNumber1).orElseThrow(
                ()->
                        new EntityNotFoundException(
                                "Worker avec l'ID = " + phoneNumber1 + " n'existe pas .",
                                ErrorCodes.WORKER_NOT_FOUND)
        );

        worker.setArchive(true);
        configureAppService.put("recommend");
        workerRepository.flush();

    }
}
