package com.projectapi.backend.service;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.repository.ChefRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.Optional;

@Data
@Service
public class ChefService {
    @Autowired
    private ChefRepository chefRepository;


    public Chef saveChef(MultipartFile avatar, String name, String email,String telephone) throws IOException {
        Chef chef = new Chef();
        final String folder = new ClassPathResource("static/PhotoPersonnel/").getFile().getAbsolutePath();
        final String route = ServletUriComponentsBuilder.fromCurrentContextPath().path("/PhotoPersonnel/").path(avatar.getOriginalFilename()).toUriString();
        byte [] bytes = avatar.getBytes();
        Path path = Paths.get(folder + File.separator +avatar.getOriginalFilename());
        Files.write(path,bytes);
        System.out.println(route);
        chef.setEmail(email);
        chef.setAvatar("/PhotoPersonnel/"+avatar.getOriginalFilename());
        chef.setNom(name);
        chef.setTelephone(telephone);
        chefRepository.save(chef);
        return chef;
    }

    public Chef findByEmailAndPassword(String email, String password){
      return chefRepository.findByEmailAndPassword(email, password);

    }

    public Chef findByTelephoneAndPassword(String telephone, String password){
        return chefRepository.findByTelephoneAndPassword(telephone, password);
    }

    public Optional<Chef> findByEmail(String email){
        return chefRepository.findByEmail(email);
    }

    public Optional<Chef> findByTelephone(String telephone){
        return chefRepository.findByTelephone(telephone);
    }

}
