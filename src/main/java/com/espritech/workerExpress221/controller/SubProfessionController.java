package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.SubProfessionDto;
import com.espritech.workerExpress221.entity.SubProfession;
import com.espritech.workerExpress221.service.SubProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category_profession")
public class SubProfessionController {

    private SubProfessionService subProfessionService;

    @PostMapping(value = "")
    public SubProfessionDto save(@RequestBody SubProfessionDto subProfessionDto
    ) throws IOException {

        System.out.println(subProfessionDto);

        return subProfessionService.save(subProfessionDto);
    }

    @GetMapping(value = "")
    public List<SubProfessionDto> findAllByArchiveFalse()
    {
        return  subProfessionService.findAllByArchiveFalse();
    }

    @PutMapping(value = "/{id}")
    public SubProfessionDto put(@PathVariable Long id, @RequestBody SubProfession subProfession)
    {
        return  subProfessionService.put( id,subProfession );
    }
}
