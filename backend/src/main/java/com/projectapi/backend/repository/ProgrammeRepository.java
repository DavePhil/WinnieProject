package com.projectapi.backend.repository;

import com.projectapi.backend.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeRepository extends JpaRepository<Programme,Long> {
}
