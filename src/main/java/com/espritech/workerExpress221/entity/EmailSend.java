package com.espritech.workerExpress221.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailSend {

    private String sender;
    private String subject;
    private String message;
}
