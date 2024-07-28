package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.PublicityDto;
import com.espritech.workerExpress221.entity.Publicity;
import com.espritech.workerExpress221.service.PublicityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publicities")
public class PublicityController {

    private PublicityService publicityService;

    @PostMapping(value = "")
    public PublicityDto save(@RequestBody PublicityDto publicityDto
    ) throws IOException {

        publicityDto.setName(publicityDto.getName().toLowerCase());
        return publicityService.save(publicityDto);
    }

    @GetMapping(value = "")
    public List<PublicityDto> findAllByArchiveFalse()
    {
        return  publicityService.findAllByArchiveFalse();
    }


    @PutMapping(value = "/{id}")
    public PublicityDto  put(@PathVariable Long id, @RequestBody Publicity publicity)
    {
        return  publicityService.put( id,publicity );
    }

    @DeleteMapping(value = "/{id}")
    public void  delete(@PathVariable Long id)
    {
        publicityService.delete(id );
    }





}
