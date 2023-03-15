package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double longitude;
    private double latitude;
    private String Location;
    @ManyToOne
    private Personnel personnel;
}
