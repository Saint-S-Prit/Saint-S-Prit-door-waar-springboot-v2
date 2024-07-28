package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Publicity extends AbstractEntity{

    private String name;

    @ManyToMany
    @JoinTable(name = "publicity_media",
            joinColumns = @JoinColumn(name = "publicity_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> publicityImage;
}
