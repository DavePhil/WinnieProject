package com.projectapi.backend.service;

import com.projectapi.backend.model.Programme;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProgrammeService {
    @Autowired
    ProgrammeRepository programmeRepository;
    public Programme saveProgramme(Programme programme){
        Programme saved = programmeRepository.save(programme);
        return saved;
    }
}
