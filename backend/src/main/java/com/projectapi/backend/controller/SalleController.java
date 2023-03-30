package com.projectapi.backend.controller;

import com.projectapi.backend.model.Salle;
import com.projectapi.backend.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SalleController {
    @Autowired
    private SalleService salleService;

    @PostMapping("/salle")
    @ResponseBody
    public Salle create(@RequestBody Salle salle){
        return salleService.saveSalle(salle);
    }
}
