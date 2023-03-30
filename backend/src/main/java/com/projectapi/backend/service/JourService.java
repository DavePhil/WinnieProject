package com.projectapi.backend.service;

import com.projectapi.backend.model.Jour;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.repository.JourRepository;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class JourService {
    @Autowired
    JourRepository jourRepository;
    public Jour saveJour(Jour jour){
        Jour saved = jourRepository.save(jour);
        return saved;
    }
}
