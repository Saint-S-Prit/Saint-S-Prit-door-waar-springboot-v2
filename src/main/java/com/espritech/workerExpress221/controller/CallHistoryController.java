package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.dto.CallHistoryDto;
import com.espritech.workerExpress221.dto.CallLogDto;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.CallHistory;
import com.espritech.workerExpress221.service.CallHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/callHistory")
public class CallHistoryController {

    private  CallHistoryService callHistoryService;

    @PostMapping("")
    public CallHistoryDto save(@RequestBody CallHistoryDto callHistoryDto)
            throws IOException {
        return callHistoryService.saveCallHistory(callHistoryDto);
    }

    @GetMapping("/{userId}")
    public List<CallHistoryDto> getCallHistoryForUser(@PathVariable Long userId) {
            return  callHistoryService.getCallHistoryForUser(userId);
    }
}
