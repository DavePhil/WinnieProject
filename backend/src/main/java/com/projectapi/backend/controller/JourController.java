package com.projectapi.backend.controller;

import com.projectapi.backend.model.Jour;
import com.projectapi.backend.service.JourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JourController {
    @Autowired
    private JourService jourService;
    @PostMapping("/jour")
    @ResponseBody
    public Jour create(@RequestBody Jour jour){
        return jourService.saveJour(jour);
    }
}
