package com.projectapi.backend.repository;

import com.projectapi.backend.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<Presence,Long> {
}
