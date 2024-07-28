package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CallLog extends AbstractEntity {
    private String clientPhoneNumber;
    private String workerPhoneNumber;
    private String callType;
    private LocalDateTime callDateTime;

    @PrePersist
    public void prePersist() {
        callDateTime = LocalDateTime.now();
    }
}
