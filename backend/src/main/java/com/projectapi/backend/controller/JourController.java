package com.projectapi.backend.controller;

import com.projectapi.backend.model.Jour;
import com.projectapi.backend.model.Presence;
import com.projectapi.backend.model.Salle;
import com.projectapi.backend.service.EvenementService;
import com.projectapi.backend.service.JourService;
import com.projectapi.backend.service.PersonnelService;
import com.projectapi.backend.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class JourController {
    @Autowired
    private JourService jourService;
    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private EvenementService evenementService;
    @PostMapping("/jour")
    @ResponseBody
    public Jour create(@RequestBody Jour jour){
        return jourService.saveJour(jour);
    }

    @GetMapping("/jours/{id}")
    @ResponseBody
    public Optional<Jour> getJour(@PathVariable("id") Long id){
        return jourService.findJourById(id);
    }

    @GetMapping("/jours")
    @ResponseBody
    public List<Jour> getJours(){
        return jourService.findJours();
    }

    @GetMapping("/jour/{id}")
    public String delete (@PathVariable("id") Long id){
        Optional<Jour> jour = jourService.findJourById(id);
        if (!jour.isPresent()) return "Jour Absent" ;
        jourService.delete(id);
        return "redirect:/jourList";
    }

    @PostMapping("/createJour")
    public String createE(@RequestParam("intitule")String intitule){
        Jour jour = new Jour();
        jour.setIntitule(intitule);
        jourService.saveJour(jour);
        return "redirect:/jourList";
    }

    @GetMapping("/jourList")
    private String presences (Model model){
        Long nbPersonnel = personnelService.count();
        Long evenement = evenementService.count();
        Long programme = programmeService.count();
        model.addAttribute("evenement", evenement);
        model.addAttribute("personnel",nbPersonnel);
        model.addAttribute("programme",programme);
        List<Jour> jours = jourService.findJours();
        model.addAttribute("jours", jours);
        return "jour.html";
    }
}
