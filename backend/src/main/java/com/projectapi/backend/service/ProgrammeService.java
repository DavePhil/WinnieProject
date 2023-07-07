package com.projectapi.backend.service;

import com.projectapi.backend.model.*;
import com.projectapi.backend.repository.ProgrammeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalTime;
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
    public Programme updateProgramme(Long id, LocalTime heureDeDebut, LocalTime heureDeFin, Evenement evenement , Salle salle, Jour jour, Personnel personnel){
        Programme current = getProgramme(id).get();
        if(heureDeFin!=null) {
            current.setHeureDeFin(heureDeFin);
            current.setHoraire(current.getHeureDeDebut()+"-"+heureDeFin);
        }
        if(heureDeDebut!=null) {
            current.setHeureDeDebut(heureDeDebut);
            current.setHoraire(heureDeDebut+"-"+current.getHeureDeFin());
        }
        if (personnel!=null) current.setPersonnel(personnel);
        if(evenement!=null) current.setEvenement(evenement);
        if(salle!=null) current.setSalle(salle);
        if(jour!=null) current.setJour(jour);
        return saveProgramme(current);
    }
    public void  delete(Long id ){
        programmeRepository.deleteById(id);
    }

    public Optional<Programme> getProgramme (Long id ){
        return programmeRepository.findById(id);
    }

    public List<Programme> programmes (){
        return programmeRepository.findAll();
    }

    public Long count(){return programmeRepository.count();}

    public List<Programme> getByJour(Long jourId) {return programmeRepository.findProgrammeByJour(jourId);}
}
