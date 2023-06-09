package com.projectapi.backend.controller;

import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.model.Programme;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.service.EvenementService;
import com.projectapi.backend.service.PersonnelService;
import com.projectapi.backend.service.ProgrammeService;
import com.projectapi.backend.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SalleController {
    @Autowired
    private SalleService salleService;
    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private EvenementService evenementService;

    @PostMapping("/salle")
    @ResponseBody
    public Salle create(@RequestBody Salle salle){
        return salleService.saveSalle(salle);
    }

    @PostMapping("/createSalle")
    public String createE(@RequestParam("intitule")String intitule){
        Salle salle = new Salle();
        salle.setIntitule(intitule);
        salleService.saveSalle(salle);
        return "redirect:/salleList";
    }

    @GetMapping("/salle/{id}")
    public String delete (@PathVariable("id") Long id){
        Optional<Salle> evenement = salleService.getSalles(id);
        if (!evenement.isPresent()) return "Evenement Absent" ;
        salleService.deleteSalle(id);
        return "redirect:/salleList";
    }

    @GetMapping("/salleList")
    private String salles (Model model){
        Long nbPersonnel = personnelService.count();
        Long evenement = evenementService.count();
        Long programme = programmeService.count();
        model.addAttribute("evenement", evenement);
        model.addAttribute("personnel",nbPersonnel);
        model.addAttribute("programme",programme);
        List<Salle> salles = salleService.salles();
        model.addAttribute("salles", salles);
        return "salle.html";
    }
}
