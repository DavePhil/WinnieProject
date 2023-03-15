package com.projectapi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String email;
    private String password;
    private String avatar;

    public Personnel (){
        super();
        password ="1234";
    }
}
