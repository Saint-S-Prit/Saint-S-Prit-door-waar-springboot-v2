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
public class Profession extends AbstractEntity {

    private String name;

    @ManyToMany
    @JoinTable(name = "profession_media",
            joinColumns = @JoinColumn(name = "profession_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> iconProfession;

    @OneToMany(mappedBy = "profession")
    private List<SubProfession> subProfessions;
}
