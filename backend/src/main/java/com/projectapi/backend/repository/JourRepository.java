package com.projectapi.backend.repository;

import com.projectapi.backend.model.Jour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourRepository extends JpaRepository<Jour, Long> {
}
