package com.espritech.workerExpress221.controller;

import com.espritech.workerExpress221.entity.EmailSend;
import com.espritech.workerExpress221.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://doorwaar.sn")
@RequestMapping("/mail")
public class EmailSendController {
    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public String sendMail(@RequestBody EmailSend emailSend) {
        return emailService.sendMail(emailSend);
    }

}
