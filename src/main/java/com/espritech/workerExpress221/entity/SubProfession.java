package com.espritech.workerExpress221.entity;

import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubProfession extends AbstractEntity {

    private String name;
    @ManyToOne
    private Profession profession;

    @ManyToMany
    @JoinTable(name = "SubProfession_media",
            joinColumns = @JoinColumn(name = "subProfession_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> iconSubProfession;
}
