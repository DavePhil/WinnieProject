package com.projectapi.backend.repository;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel,Long> {

    Personnel findByEmailAndPassword(String email, String password);
    Personnel findByTelephoneAndPassword(String telephone, String password);

    Optional<Personnel> findByEmail(String email);
    Optional<Personnel> findByTelephone(String telephone);
}
