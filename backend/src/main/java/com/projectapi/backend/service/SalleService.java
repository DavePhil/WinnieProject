package com.projectapi.backend.service;

import com.projectapi.backend.model.Salle;
import com.projectapi.backend.repository.SalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public Salle saveSalle(Salle salle){
        Salle saved = salleRepository.save(salle);
        return saved;
    }


}
