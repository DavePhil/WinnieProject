package com.projectapi.backend.service;

import com.projectapi.backend.model.Presence;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.repository.PresenceRepository;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class PresenceService {
    @Autowired
    PresenceRepository presenceRepository;
    public Presence savePresence(Presence presence){
        Presence saved = presenceRepository.save(presence);
        return saved;
    }
}
