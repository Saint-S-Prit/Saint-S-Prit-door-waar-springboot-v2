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
public class Worker extends AbstractEntity {

    private String fullName;
    //private String cin;

    private String phoneNumber;

    private Boolean recommend = false;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;


    @ManyToOne
    @JoinColumn(name = "sub_profession_id")
    private SubProfession subProfession;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String address;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(length = 500)
    private String description;

    @ManyToMany
    @JoinTable(name = "cni_media",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> cni;

    @ManyToMany
    @JoinTable(name = "avatar_media",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> avatar;

    @ManyToMany
    @JoinTable(name = "illustrator_media",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> illustrator;

    @ManyToMany
    @JoinTable(name = "certificate_media",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> certificate;

    private Boolean isAvailable = true;


    private Boolean isWorker =  true;

   // @OneToMany(mappedBy = "worker")
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CallHistory> callHistories;

}
