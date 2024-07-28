package com.espritech.workerExpress221.service.ServiceImpl;

import com.espritech.workerExpress221.config.exception.ErrorCodes;
import com.espritech.workerExpress221.config.exception.InvalidEntityException;
import com.espritech.workerExpress221.dto.ProfessionDto;
import com.espritech.workerExpress221.entity.EmailSend;
import com.espritech.workerExpress221.service.EmailService;
import com.espritech.workerExpress221.validator.EmailValidation;
import com.espritech.workerExpress221.validator.ProfessionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
    public class EmailServiceImpl implements EmailService {

        @Value("${spring.mail.username}")
        private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(EmailSend emailSend) {
        try {

            List<String> errors = EmailValidation.validate(emailSend);
            if (!errors.isEmpty()) {
                log.error("emailSend is not valid {}", emailSend);
                throw new InvalidEntityException("L'envoi de message n'est pas valide", ErrorCodes.EMAIL_SEND_NOT_VALID, errors);
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailSend.getSender());
            message.setTo(fromEmail);
            message.setSubject(emailSend.getSubject());
            message.setText(emailSend.getMessage());

            javaMailSender.send(message);

            return "Mail envoyé avec succès.";

        } catch (Exception e) {
            throw new RuntimeException("Une erreur s'est produite lors de l'envoi du mail.", e);
        }
    }



    }
