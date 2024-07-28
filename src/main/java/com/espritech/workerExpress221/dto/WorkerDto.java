package com.espritech.workerExpress221.dto;

import com.espritech.workerExpress221.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class WorkerDto {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private Boolean recommend = false;
    private Boolean isAvailable = true;
    private ProfessionDto profession;
    private SubProfessionDto subProfession;
    private RegionDto region;
    private DepartmentDto department;
    private String address;
    private ProviderType providerType;
    private String description;
    private List<MediaDto> avatar;
    private List<MediaDto> illustrator;
    private Boolean isWorker = true;
    private List<MediaDto> cni;
    private List<MediaDto> certificate;
    public static WorkerDto fromEntity(Worker worker)
    {
        if (worker == null)
        {
            log.error("worker is null");
            return null;
        }

        return WorkerDto.builder()
                .id(worker.getId())
                .fullName(worker.getFullName())
                .phoneNumber(worker.getPhoneNumber())
                .recommend(worker.getRecommend())
                .isAvailable(worker.getIsAvailable())
                .profession(ProfessionDto.fromEntity(worker.getProfession()))
                .subProfession(SubProfessionDto.fromEntity(worker.getSubProfession()))
                .region(RegionDto.fromEntity(worker.getRegion()))
                .department(DepartmentDto.fromEntity(worker.getDepartment()))
                .address(worker.getAddress())
                .providerType(worker.getProviderType())
                .description(worker.getDescription())
                .cni(
                        worker.getCni() !=null?
                                worker.getCni().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .avatar(
                        worker.getAvatar() !=null?
                                worker.getAvatar().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .illustrator(
                        worker.getIllustrator() !=null?
                                worker.getIllustrator().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .certificate(
                        worker.getCertificate() !=null?
                                worker.getCertificate().stream()
                                        .map(MediaDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .isWorker(worker.getIsWorker())
                .build();
    }


    public static Worker toEntity(WorkerDto workerDto)
    {
        if (workerDto == null)
        {
            log.error("Worker is null");
            return null;
        }

        Worker worker = new Worker();
        worker.setId(workerDto.getId());
        worker.setFullName(workerDto.getFullName());
        worker.setRecommend(workerDto.getRecommend());
        worker.setIsAvailable(workerDto.getIsAvailable());
        worker.setIsWorker(workerDto.getIsWorker());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setProfession(ProfessionDto.toEntity(workerDto.getProfession()));
        worker.setSubProfession(SubProfessionDto.toEntity(workerDto.getSubProfession()));
        worker.setRegion(RegionDto.toEntity(workerDto.getRegion()));
        worker.setDepartment(DepartmentDto.toEntity(workerDto.getDepartment()));
        worker.setAddress(workerDto.getAddress());
        worker.setProviderType(workerDto.getProviderType());
        worker.setDescription(workerDto.getDescription());
        worker.setCni(
                workerDto.getCni() !=null?
                        workerDto.getCni().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        worker.setAvatar(
                workerDto.getAvatar() !=null?
                        workerDto.getAvatar().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        worker.setIllustrator(
                workerDto.getIllustrator() !=null?
                        workerDto.getIllustrator().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        worker.setCertificate(
                workerDto.getCertificate() !=null?
                        workerDto.getCertificate().stream()
                                .map(MediaDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        return worker;

    }

}
