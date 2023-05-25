package com.projectapi.backend.service;

import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.repository.EvenementRepository;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class EvenementService {

    @Autowired
    EvenementRepository evenementRepository;
    public Evenement saveEvenement(Evenement evenement){
        Evenement saved = evenementRepository.save(evenement);
        return saved;
    }

    public void  deleteEvenement(Long id ){
        evenementRepository.deleteById(id);
    }

    public Optional<Evenement> getEvenement(Long id ){
       return evenementRepository.findById(id);
    }

    public List<Evenement> evenements (){
        return evenementRepository.findAll();
    }
}
