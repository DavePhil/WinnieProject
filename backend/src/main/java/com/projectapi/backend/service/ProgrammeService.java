package com.projectapi.backend.service;

import com.projectapi.backend.model.Programme;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProgrammeService {
    @Autowired
    ProgrammeRepository programmeRepository;
    public Programme saveProgramme(Programme programme){
        Programme saved = programmeRepository.save(programme);
        return saved;
    }

    public List<Programme> findProgrammeByPersonnel (Long idPersonnel){

        return programmeRepository.findByPersonnel(idPersonnel);
    }
    public Optional<Programme> findProgrammeById(Long id){
        return programmeRepository.findById(id);
    }

    public List<Programme> findProgrammeByPersonnelAndJour(Long personnelId, Long jourId){
        return programmeRepository.findByPersonnelAndJour(personnelId, jourId);
    }

}
