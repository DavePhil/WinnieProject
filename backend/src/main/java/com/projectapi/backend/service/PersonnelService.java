package com.projectapi.backend.service;

import com.projectapi.backend.model.Chef;
import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.repository.PersonnelRepository;
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
import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;
    public Personnel savePersonnel(MultipartFile avatar, String name, String email, String telephone) throws IOException {
        Personnel personnel = new Personnel();
        final String folder = new ClassPathResource("static/PhotoPersonnel/").getFile().getAbsolutePath();
        final String route = ServletUriComponentsBuilder.fromCurrentContextPath().path("/PhotoPersonnel/").path(avatar.getOriginalFilename()).toUriString();
        byte [] bytes = avatar.getBytes();
        Path path = Paths.get(folder + File.separator +avatar.getOriginalFilename());
        Files.write(path,bytes);
        System.out.println(route);
        personnel.setEmail(email);
        personnel.setAvatar("/PhotoPersonnel/"+avatar.getOriginalFilename());
        personnel.setNom(name);
        personnel.setTelephone(telephone);
        personnelRepository.save(personnel);
        return personnel;
    }

    public void delete(Long id ){
        personnelRepository.deleteById(id);
    }

    public Personnel findByEmailAndPassword(String email, String password){
       return personnelRepository.findByEmailAndPassword(email, password);

    }
    public Personnel findByTelephoneAndPassword(String telephone, String password){
       return personnelRepository.findByTelephoneAndPassword(telephone, password);

    }
    public List<Personnel> personnels(){
        return personnelRepository.findAll();
    }



    public Optional<Personnel> findByEmail(String email){
        return personnelRepository.findByEmail(email);
    }

    public Optional<Personnel> findByTelephone(String telephone){
        return personnelRepository.findByTelephone(telephone);
    }
}
