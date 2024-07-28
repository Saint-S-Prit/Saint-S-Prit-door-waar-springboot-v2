package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Media extends AbstractEntity {
    private String image;
}
