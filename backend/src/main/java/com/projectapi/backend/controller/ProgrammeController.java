package com.projectapi.backend.controller;

import com.projectapi.backend.model.*;
import com.projectapi.backend.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;

    @PostMapping("/programme")
    @ResponseBody
    public Programme create(@RequestParam("horaire") String horaire,
                            @RequestParam("salle") Salle salle,
                            @RequestParam("evenement")Evenement evenement,
                            @RequestParam("jour") Jour jour,
                            @RequestParam("personnel")Personnel personnel){
        Programme programme = new Programme();
        programme.setEvenement(evenement);
        programme.setJour(jour);
        programme.setSalle(salle);
        programme.setPersonnel(personnel);
        programme.setHoraire(horaire);
        return programmeService.saveProgramme(programme);
    }

    @GetMapping("/programmebyPersonnel")
    @ResponseBody
    public List<Programme> getProgrammeByPersonnel(@PathVariable("personnelId") Long personnelId){
        return programmeService.findProgrammeByPersonnel(personnelId);
    }

    @GetMapping("/programmebyPersonnelAndJour/{personnelId}/{jourId}")
    @ResponseBody
    public List<Programme> getProgrammeByPersonnelAndJour(@PathVariable("personnelId") Long personnelId,
                                                          @PathVariable("jourId")Long jourId){
        return programmeService.findProgrammeByPersonnelAndJour(personnelId,jourId);
    }
    @GetMapping("/programmeById/{id}")
    @ResponseBody
    public Optional<Programme> getProgrammeById(@PathVariable("id") Long id){
        return programmeService.findProgrammeById(id);
    }

}
