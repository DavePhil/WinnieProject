package com.projectapi.backend.controller;

import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.model.Presence;
import com.projectapi.backend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PresenceController {
    @Autowired
    private PresenceService presenceService;
    @PostMapping("/presence")
    @ResponseBody
    public Presence create(@RequestParam("longitude") Double longitude,
                           @RequestParam("latitude") Double latitude,
                           @RequestParam("location") String location,
                           @RequestParam("personnel")Personnel personnel){
        Presence presence = new Presence();
        presence.setLocation(location);
        presence.setLatitude(latitude);
        presence.setLongitude(longitude);
        presence.setLocation(location);
        presence.setPersonnel(personnel);
        return presenceService.savePresence(presence);
    }

    @GetMapping("/presence/{id}")
    public String delete (@PathVariable("id") Long id){
        Optional<Presence> presence = presenceService.getPresence(id);
        if (!presence.isPresent()) return "Evenement Absent" ;
        presenceService.deletePresence(id);
        return "redirect:/presenceList";
    }

    @GetMapping("/presenceList")
    private String presences (Model model){
        List<Presence> presences = presenceService.presences();
        model.addAttribute("presences", presences);
        return "presence.html";
    }
}
