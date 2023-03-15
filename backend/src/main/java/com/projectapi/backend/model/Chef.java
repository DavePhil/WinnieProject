package com.projectapi.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String telephone;
    private String email;
    private String password;
    private String avatar;
    @OneToMany
    @JoinColumn(name = "chef_id", referencedColumnName = "id")
    private List<Personnel> personnels;

    public Chef() {
        super();
        password = "1234";
    }
}
