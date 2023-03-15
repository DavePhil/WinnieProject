package com.projectapi.backend.repository;

import com.projectapi.backend.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
