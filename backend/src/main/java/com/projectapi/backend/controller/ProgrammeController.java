package com.projectapi.backend.controller;

import com.projectapi.backend.model.*;
import com.projectapi.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;
    @Autowired
    private SalleService salleService;
    @Autowired
    private EvenementService evenementService;
    @Autowired
    private JourService jourService;
    @Autowired
    private PersonnelService personnelService;

    @PostMapping("/programme")
    public String create(@RequestParam("heureDeDebut")  @DateTimeFormat(pattern = "HH:mm") LocalTime heureDeDebut,
                            @RequestParam("heureDeFin") @DateTimeFormat(pattern = "HH:mm")LocalTime heureDeFin,
                            @RequestParam("salle") Salle salle,
                            @RequestParam("evenement")Evenement evenement,
                            @RequestParam("jour") Jour jour,
                            @RequestParam("personnel")Personnel personnel){
        Programme programme = new Programme();
        programme.setEvenement(evenement);
        programme.setJour(jour);
        programme.setSalle(salle);
        programme.setPersonnel(personnel);
        programme.setHeureDeDebut(heureDeDebut);
        programme.setHeureDeFin(heureDeFin);
        programme.setHoraire(heureDeDebut + "-" +heureDeFin);
        programmeService.saveProgramme(programme);
        return "redirect:/programmeList";
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
    public String getProgrammeById(@PathVariable("id") Long id,Model model){
        Optional<Programme> programme = programmeService.findProgrammeById(id);
        model.addAttribute("programme",programme.get());

        return "soustimetable.html";
    }
    @PostMapping("/createProgramme")
    public String createE(@RequestParam("horaire") String horaire,
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
        programmeService.saveProgramme(programme);
        return "redirect:/programmeList";
    }

    @GetMapping("/programme/{id}")
    public String delete (@PathVariable("id") Long id){
        Optional<Programme> programme = programmeService.getProgramme(id);
        if (!programme.isPresent()) return "Evenement Absent" ;
        programmeService.delete(id);
        return "redirect:/programmeList";
    }
    @PostMapping("/programmec/{id}")
    public  String updateProgramme(@PathVariable("id") Long id, Model model,
                                   @RequestParam(value = "heureDeDebut", required = false)  @DateTimeFormat(pattern = "HH:mm") LocalTime heureDeDebut,
                                   @RequestParam(value = "heureDeFin", required = false) @DateTimeFormat(pattern = "HH:mm")LocalTime heureDeFin,
                                   @RequestParam(value = "salle", required = false) Salle salle,
                                   @RequestParam(value = "evenement", required = false)Evenement evenement,
                                   @RequestParam(value = "jour", required = false) Jour jour,
                                   @RequestParam(value = "personnel", required = false)Personnel personnel) {

        Programme current = programmeService.updateProgramme(id,heureDeDebut,heureDeFin,evenement,salle,jour,personnel);
        model.addAttribute("programme",current);
        return "redirect:/programmeList";
    }

    @GetMapping("/programmeList")
    private String salles (Model model){
//        Long nbPersonnel = personnelService.count();
//        Long evenement = evenementService.count();
//        Long programme = programmeService.count();
//        model.addAttribute("nbevenement", evenement);
//        model.addAttribute("personnels",nbPersonnel);
//        model.addAttribute("programme",programme);
        List<Programme> programmeLun = programmeService.getByJour(1L);
        List<Programme> programmeMar = programmeService.getByJour(2L);
        List<Programme> programmeMer = programmeService.getByJour(3L);
        List<Programme> programmeJeu = programmeService.getByJour(4L);
        List<Programme> programmeVen = programmeService.getByJour(5L);
        List<Programme> programmeSam = programmeService.getByJour(6L);
        List<Programme> programmeDim = programmeService.getByJour(7L);
        List<Personnel> personnels = personnelService.personnels();
        List<Evenement> evenements = evenementService.evenements();
        List<Salle> salles = salleService.salles();
        List<Jour> jours = jourService.findJours();
        model.addAttribute("programmeLun", programmeLun);
        model.addAttribute("programmeMar", programmeMar);
        model.addAttribute("programmeMer", programmeMer);
        model.addAttribute("programmeJeu", programmeJeu);
        model.addAttribute("programmeVen", programmeVen);
        model.addAttribute("programmeSam", programmeSam);
        model.addAttribute("programmeDim", programmeDim);
        model.addAttribute("personnel", personnels);
        model.addAttribute("evenement", evenements);
        model.addAttribute("jour", jours);
        model.addAttribute("salle",salles);

        return "timeTable.html";
    }

    @GetMapping("/modif/{id}")
    public String modif(Model model, @PathVariable("id") Long id){
        Optional<Programme> programmes = programmeService.findProgrammeById(id);
        List<Personnel> personnels = personnelService.personnels();
        List<Evenement> evenements = evenementService.evenements();
        List<Salle> salles = salleService.salles();
        List<Jour> jours = jourService.findJours();
        model.addAttribute("programme", programmes.get());
        model.addAttribute("personnel", personnels);
        model.addAttribute("evenement", evenements);
        model.addAttribute("jour", jours);
        model.addAttribute("salle",salles);

        return "modifierProgramme.html";
    }

    @GetMapping("/deleteprogramme/{id}")
    public String deleteProgramme(@PathVariable("id") Long id){
        programmeService.delete(id);
        return "redirect:/programmeList";
    }

}
