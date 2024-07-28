package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CallHistory  extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;


    @Enumerated(EnumType.STRING)
    private TypeCall typeCall;

}
