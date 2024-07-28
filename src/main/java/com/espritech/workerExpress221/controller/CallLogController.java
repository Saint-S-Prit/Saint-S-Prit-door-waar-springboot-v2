package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.CallLogDto;
import com.espritech.workerExpress221.service.CallLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/callLog")
public class CallLogController {



    private CallLogService callLogService;

    @PostMapping(value = "")
    public CallLogDto save(@RequestBody CallLogDto callLogDto
    ) throws IOException {
        return callLogService.save(callLogDto);
    }

    @GetMapping(value = "")
    public List<CallLogDto> findAll()
    {
        return  callLogService.findAll();
    }

    @GetMapping(value = "/{clientPhoneNumber}")
    public List<CallLogDto> get(@PathVariable String clientPhoneNumber)
    {
        return callLogService.findByClientPhoneNumber(clientPhoneNumber);
    }
}
