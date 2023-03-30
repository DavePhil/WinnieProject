package com.projectapi.backend.controller;

import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.model.Presence;
import com.projectapi.backend.service.EvenementService;
import com.projectapi.backend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvenementController {
    @Autowired
    private EvenementService evenementService;
    @PostMapping("/evenement")
    @ResponseBody
    public Evenement create(@RequestBody Evenement evenement){
        return evenementService.saveEvenement(evenement);
    }
}
