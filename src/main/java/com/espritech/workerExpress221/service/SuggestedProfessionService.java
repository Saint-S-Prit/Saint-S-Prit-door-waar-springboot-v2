package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.dto.SuggestedProfessionDto;
import com.espritech.workerExpress221.entity.SuggestedProfession;

import java.util.List;

public interface SuggestedProfessionService {



    SuggestedProfessionDto save(SuggestedProfessionDto suggestedProfessionDto);
    List<SuggestedProfessionDto> findAll();
    List<SuggestedProfessionDto> findAllByArchiveFalse();
    SuggestedProfessionDto find(Long id);
    SuggestedProfessionDto put(Long id, SuggestedProfession suggestedProfession);
    void delete(Long id);
}
