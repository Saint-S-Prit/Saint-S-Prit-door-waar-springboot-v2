package com.espritech.workerExpress221.entity;


import com.espritech.workerExpress221.utils.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ReviewApp extends AbstractEntity {

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private UserApp userApp;

    private String role;

    private int rating; // Note (peut être un nombre d'étoiles, par exemple)

    @Column(length = 500)
    private String comment; // Commentaire de l'avis

    private Boolean isPublish = false;

    // Getters et Setters
}
