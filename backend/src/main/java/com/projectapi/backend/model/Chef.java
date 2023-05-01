package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@Entity
@DynamicUpdate
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Column(unique = true)
    private String telephone;
    @Column(unique = true)
    private String email;

    private String password;
    private String avatar;
    @Transient
    private String response;
    @OneToMany
    @JoinColumn(name = "chef_id", referencedColumnName = "id")
    private List<Personnel> personnels;

    public Chef() {
        super();
        password = "1234";
    }

}
