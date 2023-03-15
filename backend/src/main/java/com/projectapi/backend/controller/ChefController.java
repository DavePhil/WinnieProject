package com.projectapi.backend.controller;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ChefController {

    @Autowired
    ChefService chefService;

    @PostMapping("/chef")
    @ResponseBody
    public Chef createChef(@RequestParam("avatar")MultipartFile avatar,
                           @RequestParam("nom") String nom,
                           @RequestParam("email") String email,
                           @RequestParam("telephone") String telephone) throws IOException {
        if(!avatar.getContentType().equals("image/jpeg") && !avatar.getContentType().equals("image/png"));
        if(chefService.findByEmail(email).isPresent() || chefService.findByTelephone(telephone).isPresent()) return null; // verifie la présence de l'email ou du télephone pour empêcher les doublons
        return chefService.saveChef(avatar,nom,email,telephone);

    }

    @ResponseBody
    @GetMapping("/getchefbyemailandpassword/{email}/{password}")
    public Optional<Chef> getChefByEmailAndPassword(@PathVariable("email") String email,
                                              @PathVariable("password") String password){

        return chefService.findByEmailAndPassword(email, password);

    }

    @ResponseBody
    @GetMapping("/getchefbytelephoneandpassword/{telephone}/{password}")
    public Optional<Chef> getchefByTelephoneAndPassword(@PathVariable("telephone") String telephone,
                                              @PathVariable("password") String password){
        return chefService.findByTelephoneAndPassword(telephone, password);
    }
}
