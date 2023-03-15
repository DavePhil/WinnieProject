package com.projectapi.backend.repository;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef,Long> {

    Optional<Chef> findByEmailAndPassword(String email, String password);
    Optional<Chef> findByTelephoneAndPassword(String telephone, String password);

    Optional<Chef> findByEmail(String email);
    Optional<Chef> findByTelephone(String telephone);


}
