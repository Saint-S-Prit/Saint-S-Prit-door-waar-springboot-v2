package com.espritech.workerExpress221.service;

import com.espritech.workerExpress221.entity.EmailSend;

public interface EmailService  {
    String sendMail(EmailSend emailSend);
}
