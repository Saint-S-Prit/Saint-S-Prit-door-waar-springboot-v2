package com.espritech.workerExpress221.service.ServiceImpl;
import com.espritech.workerExpress221.dto.WorkerDto;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.repository.WorkerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaginationService {

    public <T, D> PaginationResponse<D> findAllWithPaginator(
            int pageNumber, int pageSize, JpaRepository<T, Long> repository, Function<T, D> converter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<T> page = repository.findAll(pageable);
        List<D> content = page.getContent().stream().map(converter).collect(Collectors.toList());
        PaginationResponse<D> paginationResponse = new PaginationResponse<>();
        paginationResponse.setContent(content);
        paginationResponse.setNumberPage(page.getNumber());
        paginationResponse.setNumberSize(page.getSize());
        paginationResponse.setTotalElement(page.getTotalElements());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setLast(page.isLast());
        return paginationResponse;
    }


    public PaginationResponse<WorkerDto> findAllByFullNameAndIsWorkerTrueWithPaginator(
            String fullName, int pageNumber, int pageSize, WorkerRepository repository) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Worker> page = repository.findAllByFullNameAndIsWorkerTrueWithPaginator(fullName, pageable);

        List<WorkerDto> content = page.getContent().stream()
                .map(WorkerDto::fromEntity)
                .collect(Collectors.toList());

        PaginationResponse<WorkerDto> paginationResponse = new PaginationResponse<>();
        paginationResponse.setContent(content);
        paginationResponse.setNumberPage(page.getNumber());
        paginationResponse.setNumberSize(page.getSize());
        paginationResponse.setTotalElement(page.getTotalElements());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setLast(page.isLast());
        return paginationResponse;
    }
}
