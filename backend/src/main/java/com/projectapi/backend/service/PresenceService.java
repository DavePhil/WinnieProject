package com.projectapi.backend.service;

import com.projectapi.backend.model.Presence;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.repository.PresenceRepository;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class PresenceService {
    @Autowired
    PresenceRepository presenceRepository;
    public Presence savePresence(Presence presence){
        Presence saved = presenceRepository.save(presence);
        return saved;
    }

    public void  deletePresence(Long id ){
        presenceRepository.deleteById(id);
    }

    public Optional<Presence> getPresence(Long id ){
        return presenceRepository.findById(id);
    }

    public List<Presence> presences (){
        return presenceRepository.findAll();
    }

    public Long count(){return presenceRepository.count();}
}
