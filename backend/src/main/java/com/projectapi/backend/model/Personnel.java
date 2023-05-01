package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telephone;
    private String nom;
    @Column(unique = true)
    private String email;
    private String password;
    private String avatar;
    @Transient
    private String response;

    public Personnel (){
        super();
        password ="1234";
    }
}
