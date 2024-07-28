package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.RegionDto;
import com.espritech.workerExpress221.entity.Region;
import com.espritech.workerExpress221.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/regions")
public class RegionController {

    private RegionService regionService;

    @PostMapping(value = "")
    public RegionDto save(@RequestBody RegionDto regionDto
    ) throws IOException {
        return regionService.save(regionDto);
    }

    @GetMapping(value = "")
    public List<RegionDto> findAllByArchiveFalse()
    {
        return  regionService.findAllByArchiveFalse();
    }

    @GetMapping(value = "/{id}")
    public RegionDto find(@PathVariable Long id)
    {

        return  regionService.find(id);
    }

    @PutMapping(value = "/{id}")
    public RegionDto put(@PathVariable Long id, @RequestBody Region region)
    {
        return  regionService.put( id,region );
    }
}
