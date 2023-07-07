package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalTime;

@Data
@Entity
@DynamicUpdate
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime heureDeDebut;
    private LocalTime heureDeFin;
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
