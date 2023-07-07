package com.projectapi.backend.service;

import com.projectapi.backend.model.Jour;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.repository.JourRepository;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class JourService {
    @Autowired
    JourRepository jourRepository;
    public Jour saveJour(Jour jour){
        Jour saved = jourRepository.save(jour);
        return saved;
    }

    public Optional<Jour> findJourById(Long id){
        return jourRepository.findById(id);
    }

    public List<Jour> findJours(){
        return jourRepository.findAll();
    }

    public void delete (Long id ){
         jourRepository.deleteById(id);
    }

    public Long count(){return jourRepository.count();}
}
