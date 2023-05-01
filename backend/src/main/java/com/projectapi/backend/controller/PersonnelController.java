package com.projectapi.backend.controller;

import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PersonnelController {
    @Autowired
    PersonnelService personnelService;

    @ResponseBody
    @PostMapping("/personnel")
    public Personnel createPersonnel(@RequestParam("avatar") MultipartFile avatar,
                                     @RequestParam("nom") String nom,
                                     @RequestParam("email") String email,
                                     @RequestParam("telephone") String telephone) throws IOException {
        Personnel personnel = new Personnel();
        if(!avatar.getContentType().equals("image/jpeg") && !avatar.getContentType().equals("image/png")) {
            personnel.setResponse("Seulement les images jpeg ou png sont acceptees");
            return personnel;
        };
        if(personnelService.findByEmail(email).isPresent() || personnelService.findByTelephone(telephone).isPresent()){
            personnel.setResponse("L'utilisateur existe deja");
            return personnel;// verifie la présence de l'email ou du télephone pour empêcher les doublons
        }
        return personnelService.savePersonnel(avatar,nom,email,telephone);
    }

    @ResponseBody
    @GetMapping("/login/{email}/{password}")
    public Personnel getPersonnelByEmailAndPassword(@PathVariable("email") String email,
                                              @PathVariable("password") String password){
        return personnelService.findByEmailAndPassword(email, password);

    }

    @ResponseBody
    @GetMapping("/logint/{telephone}/{password}")
    public  Personnel getChefByTelephoneAndPassword(@PathVariable("telephone") String telephone,
                                              @PathVariable("password") String password){
         return  personnelService.findByTelephoneAndPassword(telephone, password);
    }


}
