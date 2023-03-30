package com.projectapi.backend.controller;

import com.projectapi.backend.model.Presence;
import com.projectapi.backend.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PresenceController {
    @Autowired
    private PresenceService presenceService;
    @PostMapping("/presence")
    @ResponseBody
    public Presence create(@RequestBody Presence presence){
        return presenceService.savePresence(presence);
    }
}
