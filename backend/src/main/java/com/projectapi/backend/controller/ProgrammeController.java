package com.projectapi.backend.controller;

import com.projectapi.backend.model.Programme;
import com.projectapi.backend.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;

    @PostMapping("/programme")
    @ResponseBody
    public Programme create(@RequestBody Programme programme){
        return programmeService.saveProgramme(programme);
    }
}
