package com.projectapi.backend.controller;

import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.model.Presence;
import com.projectapi.backend.service.EvenementService;
import com.projectapi.backend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EvenementController {
    @Autowired
    private EvenementService evenementService;
    @PostMapping("/evenement")
    @ResponseBody
    public Evenement create(@RequestBody Evenement evenement){
        return evenementService.saveEvenement(evenement);
    }

    @PostMapping("/evenementWeb")
    public String createE(@RequestParam("intitule")String intitule){
        Evenement evenement = new Evenement();
        evenement.setIntitule(intitule);
         evenementService.saveEvenement(evenement);
         return "redirect:/";
    }

    @GetMapping("/evenement/{id}")
    public String delete (@PathVariable("id") Long id){
        Optional<Evenement> evenement = evenementService.getEvenement(id);
        if (!evenement.isPresent()) return "Evenement Absent" ;
        evenementService.deleteEvenement(id);
        return "redirect:/";
    }

    @GetMapping("/evenements")
    private List<Evenement> Evenements (){
        return evenementService.evenements();
    }
}
