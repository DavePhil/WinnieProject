package com.projectapi.backend.controller;

import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    public Map<String, String> getPersonnelByEmailAndPassword(@PathVariable("email") String email,
                                              @PathVariable("password") String password){
       Optional<Personnel> personnel = personnelService.findByEmailAndPassword(email, password);
       String response ="";
        HashMap<String, String> map = new HashMap<>();
       if(personnel.isPresent()) response = "Connexion reussie";
       else response = "Impossible de vous connecter";
       map.put("response", response);
       return map;
    }

    @ResponseBody
    @GetMapping("/logint/{telephone}/{password}")
    public Map<String, String> getChefByTelephoneAndPassword(@PathVariable("telephone") String telephone,
                                              @PathVariable("password") String password){
        Optional<Personnel>  personnel = personnelService.findByTelephoneAndPassword(telephone, password);
        String response ="";
        HashMap<String, String> map = new HashMap<>();
        if(personnel.isPresent()) response = "Connexion reussie";
        else response = "Impossible de vous connecter";
        map.put("response", response);
        return map;
    }


}
