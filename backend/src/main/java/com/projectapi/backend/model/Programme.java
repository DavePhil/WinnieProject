package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horaire;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Evenement evenement;
    @ManyToOne
    private Jour jour;
    @ManyToOne
    private Personnel personnel;

}
