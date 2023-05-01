package com.projectapi.backend.controller;

import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.model.Presence;
import com.projectapi.backend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.desktop.PreferencesEvent;

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
        return presenceService.savePresence(presence);
    }
}
