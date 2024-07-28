package com.espritech.workerExpress221.controller;


import com.espritech.workerExpress221.dto.WorkerDto;
import com.espritech.workerExpress221.entity.Media;
import com.espritech.workerExpress221.entity.Worker;
import com.espritech.workerExpress221.service.ServiceImpl.PaginationResponse;
import com.espritech.workerExpress221.service.WorkerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/workers")
@CrossOrigin(origins = "http://localhost:3000")
public class WorkerController {

    private WorkerService workerService;
    @PostMapping(value = "")
    public WorkerDto save(@RequestBody WorkerDto workerDto
    ) throws IOException {
        return workerService.save(workerDto);
    }

    @GetMapping(value = "")
    public List<WorkerDto> findAllByArchiveFalseAndIsWorkerTrue()
    {
        return  workerService.findAllByArchiveFalseAndIsWorkerTrue();
    }


    @GetMapping("/pagination/{numberPage}/{pageSize}")
    public PaginationResponse<WorkerDto> findAllWithPaginator(@PathVariable int numberPage, @PathVariable int pageSize) {
        return workerService.findAllWithPaginator(numberPage, pageSize);
    }


    @GetMapping("/pagination")
    public PaginationResponse<WorkerDto> findAllByFullNameAndIsWorkerTrueWithPaginator(
            @RequestParam(value = "fullName", defaultValue = "") String fullName,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return workerService.findAllByFullNameAndIsWorkerTrueWithPaginator(fullName, pageNumber, pageSize);
    }

    @GetMapping(value = "/pagination/{numberPage}/{sizePage}/{field}")
    public Page<Worker> findAllWithPaginatorAndSort(@PathVariable int numberPage, @PathVariable int sizePage, @PathVariable String field)
    {
        return  workerService.findAllWithPaginatorAndSort(numberPage, sizePage, field);
    }

    @GetMapping(value = "/recommend")
    public List<WorkerDto> findAllByRecommendTrueAndIsWorkerTrue()
    {
        return  workerService.findAllByRecommendTrueAndIsWorkerTrue();
    }



    @GetMapping(value = "/admin")
    public List<WorkerDto> findAll()
    {
        return  workerService.findAll();
    }

    @GetMapping(value = "/{phoneNumber}")
    public WorkerDto findByPhoneNumber(@PathVariable String phoneNumber)
    {
        return  workerService.findByPhoneNumber(phoneNumber);
    }


    @GetMapping(value = "/search/profession")
    public List<WorkerDto> findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(@RequestParam(required = false) String profession, @RequestParam(required = false) String region, @RequestParam(required = false) String department) {
            return workerService.findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(profession, region, department);

    }


    @PutMapping(value = "/{phoneNumber}")
    public WorkerDto put(@PathVariable String phoneNumber, @RequestBody Worker worker)
    {
        return  workerService.put(phoneNumber, worker);
    }

    @GetMapping(value = "/isWorker/{phoneNumber}")
    public String isWorker(@PathVariable String phoneNumber)
    {
        return workerService.isWorker(phoneNumber);

    }



    @GetMapping(value = "/isRecommendWorker/{phoneNumber}")
    public String isRecommendWorker(@PathVariable String phoneNumber)
    {
        return workerService.isRecommendWorker(phoneNumber);

    }

    @GetMapping(value = "/isAvailable/{phoneNumber}")
    public String isAvailable(@PathVariable String phoneNumber)
    {
        return workerService.isAvailable(phoneNumber);

    }


    @GetMapping(value = "/en-attente")
    public WorkerDto findAllByArchiveFalseAndIsWorkerFalse()
    {
        return (WorkerDto) workerService.findAllByArchiveFalseAndIsWorkerFalse();

    }


    @PutMapping(value = "/add-cni/{phoneNumber}/{cni}")
    public WorkerDto editWorkerAddCni(@PathVariable String phoneNumber, @PathVariable Long cni)
    {
        return workerService.editWorkerAddCni(phoneNumber, cni);

    }

    @PutMapping(value = "/edite-avatar/{phoneNumber}/{avatar}")
    public WorkerDto updateAvatar(@PathVariable String phoneNumber, @PathVariable Long avatar)
    {
        return workerService.updateAvatar(phoneNumber, avatar);

    }

    @PutMapping(value = "/edite-certificate/{phoneNumber}/{certificate}")
    public WorkerDto updateCertificate(@PathVariable String phoneNumber, @PathVariable Long certificate)
    {
        return workerService.updateCertificate(phoneNumber, certificate);

    }


    @PutMapping(value = "/edite-illustrator/{phoneNumber}")
    public WorkerDto updateIllustrations(@PathVariable String phoneNumber, @RequestBody List<Media> newIllustrations)
    {
        return workerService.updateIllustrations(phoneNumber , newIllustrations);
    }


}






















