package com.projectapi.backend.controller;

import com.projectapi.backend.model.Jour;
import com.projectapi.backend.service.JourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class JourController {
    @Autowired
    private JourService jourService;
    @PostMapping("/jour")
    @ResponseBody
    public Jour create(@RequestBody Jour jour){
        return jourService.saveJour(jour);
    }

    @GetMapping("/jour")
    @ResponseBody
    public Optional<Jour> getJour(@PathVariable("id") Long id){
        return jourService.findJourById(id);
    }

    @GetMapping("/jours")
    @ResponseBody
    public List<Jour> getJours(){
        return jourService.findJours();
    }
}
