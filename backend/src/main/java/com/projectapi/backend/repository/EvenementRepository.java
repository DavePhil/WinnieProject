package com.projectapi.backend.repository;

import com.projectapi.backend.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement,Long> {
}
