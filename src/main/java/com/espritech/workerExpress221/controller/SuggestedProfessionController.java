package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.SuggestedProfessionDto;
import com.espritech.workerExpress221.entity.SuggestedProfession;
import com.espritech.workerExpress221.service.SuggestedProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/suggestedProfession")
public class SuggestedProfessionController {


    private SuggestedProfessionService suggestedProfessionService;



    @PostMapping(value = "")
    public SuggestedProfessionDto save(@RequestBody SuggestedProfessionDto suggestedProfessionDto
    ) throws IOException {
        return suggestedProfessionService.save(suggestedProfessionDto);
    }

    @GetMapping(value = "")
    public List<SuggestedProfessionDto> findAllByArchiveFalse()
    {
        return  suggestedProfessionService.findAllByArchiveFalse();
    }

    @GetMapping(value = "/{id}")
    public SuggestedProfessionDto find(@PathVariable Long id)
    {

        return  suggestedProfessionService.find(id);
    }

    @PutMapping(value = "/{id}")
    public SuggestedProfessionDto put(@PathVariable Long id, @RequestBody SuggestedProfession suggestedProfession)
    {
        return  suggestedProfessionService.put( id,suggestedProfession );
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id)
    {

        suggestedProfessionService.delete(id);
    }

}
