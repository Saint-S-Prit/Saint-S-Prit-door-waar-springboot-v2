package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.dto.DepartmentDto;
import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Region extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "region")
    private List<Department> departments;

}
