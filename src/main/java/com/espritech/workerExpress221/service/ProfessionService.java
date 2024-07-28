package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.service.ServiceImpl.PaginationResponse;

import java.util.List;

public interface ProfessionService {


    ProfessionDto save(ProfessionDto professionDto);
    List<ProfessionDto> findAll();
    PaginationResponse<ProfessionDto> findAllWithPaginator(int pageNumber, int pageSize);

    List<ProfessionDto> findAllByArchiveFalse();
    ProfessionDto find(Long id);
    ProfessionDto put(Long id, Profession profession);
    void delete(Long id);
}
