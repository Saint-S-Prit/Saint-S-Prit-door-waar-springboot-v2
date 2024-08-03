package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.WorkerDto;
import com.espritech.workerExpress221.entity.Media;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.service.ServiceImpl.PaginationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkerService {
    WorkerDto save(WorkerDto workerDto);
    List<WorkerDto> findAll();
    PaginationResponse<WorkerDto> findAllWithPaginator(int pageNumber, int pageSize);
    PaginationResponse<WorkerDto>  findAllByFullNameAndIsWorkerTrueWithPaginator(String fullName, int pageNumber, int pageSize);
    Page<Worker>  findAllWithPaginatorAndSort(int pageNumber, int pageSize, String field);
    List<WorkerDto> findAllByRecommendTrueAndIsWorkerTrue();
    List<WorkerDto> findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(String profession, String region, String department);
    WorkerDto findByPhoneNumber(String phoneNumber);
    WorkerDto put(String phoneNumber, Worker worker);
    WorkerDto editWorkerAddCni(String phoneNumber,Long cni);
    WorkerDto updateAvatar(String phoneNumber,Long avatar);
    WorkerDto updateCertificate(String phoneNumber, Long certificate);
    WorkerDto updateIllustrations(String phoneNumber, List<Media> newIllustrations);
    String isWorker(String phoneNumber);
    String isRecommendWorker(String phoneNumber);
    String isAvailable(String phoneNumber);
    void delete(String phoneNumber);
    void deleteAllByPhoneNumber(String phoneNumber);
    List<WorkerDto> findAllByArchiveFalseAndIsWorkerTrue();
    List<WorkerDto> findAllByArchiveFalseAndIsWorkerFalse();
}
