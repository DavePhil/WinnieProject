package com.projectapi.backend.controller;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.model.Evenement;
import com.projectapi.backend.service.ChefService;
import com.projectapi.backend.service.EvenementService;
import com.projectapi.backend.service.PersonnelService;
import com.projectapi.backend.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ChefController {

    @Autowired
    ChefService chefService;
    @Autowired
    EvenementService evenementService;
    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private ProgrammeService programmeService;


    @PostMapping("/chef")
    @ResponseBody
    public Chef createChef(@RequestParam("avatar")MultipartFile avatar,
                           @RequestParam("nom") String nom,
                           @RequestParam("email") String email,
                           @RequestParam("telephone") String telephone) throws IOException {
        Chef chef = new Chef();
        if(!avatar.getContentType().equals("image/jpeg") && !avatar.getContentType().equals("image/png")){
            chef.setResponse("Seulement les images jpeg ou png sont acceptees");
            return chef;
        }
        if(chefService.findByEmail(email).isPresent() || chefService.findByTelephone(telephone).isPresent()){
            chef.setResponse("L'utilisateur existe deja");
            return chef;// verifie la présence de l'email ou du télephone pour empêcher les doublons
        }
        return chefService.saveChef(avatar,nom,email,telephone);

    }

    @ResponseBody
    @GetMapping("/getchefbyemailandpassword/{email}/{password}")
    public Chef getChefByEmailAndPassword(@PathVariable("email") String email,
                                                    @PathVariable("password") String password){
        return chefService.findByEmailAndPassword(email, password);
    }

    @ResponseBody
    @GetMapping("/getchefbytelephoneandpassword/{telephone}/{password}")
    public Chef getchefByTelephoneAndPassword(@PathVariable("telephone") String telephone,
                                                        @PathVariable("password") String password){
        return  chefService.findByTelephoneAndPassword(telephone, password);
    }

    @GetMapping("/")
    public String HomePagE(Model model)
    {
        Long nbPersonnel = personnelService.count();
        Long evenement = evenementService.count();
        Long programme = programmeService.count();
        model.addAttribute("evenement", evenement);
        model.addAttribute("personnel",nbPersonnel);
        model.addAttribute("programme",programme);
       List<Evenement> evenements = evenementService.evenements();
       model.addAttribute("evenements", evenements);
        return "/index.html";

    }
}
