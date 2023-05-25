package com.projectapi.backend.service;

import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.repository.SalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public Salle saveSalle(Salle salle){
        Salle saved = salleRepository.save(salle);
        return saved;
    }

    public void  deleteSalle(Long id ){
        salleRepository.deleteById(id);
    }

    public Optional<Salle> getSalles(Long id ){
        return salleRepository.findById(id);
    }

    public List<Salle> salles (){
        return salleRepository.findAll();
    }


}
