package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.Profession;
import com.espritech.workerExpress221.service.ProfessionService;
import com.espritech.workerExpress221.service.ServiceImpl.PaginationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/professions")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessionController {

    private ProfessionService professionService;

    @PostMapping(value = "")
    public ProfessionDto save(@RequestBody ProfessionDto professionDto
    ) throws IOException {
        professionDto.setName(professionDto.getName().toLowerCase());
        return professionService.save(professionDto);
    }

    @GetMapping(value = "")
    public List<ProfessionDto> findAllByArchiveFalse()
    {
        return  professionService.findAllByArchiveFalse();
    }


    @GetMapping("/pagination/{numberPage}/{pageSize}")
    public PaginationResponse<ProfessionDto> findAllProfessionWithPaginator(@PathVariable int numberPage, @PathVariable  int pageSize)
    {
        return  professionService.findAllWithPaginator(numberPage, pageSize);
    }




    @GetMapping(value = "/{id}")
    public ProfessionDto get(@PathVariable Long id)
    {
        return  professionService.find(id);
    }


    @PutMapping(value = "/{id}")
    public ProfessionDto  put(@PathVariable Long id, @RequestBody Profession profession)
    {
        return  professionService.put( id,profession );
    }





}
