package com.critter.chronologer.controller;

import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.Users;
import com.critter.chronologer.repository.PetsRepository;
import com.critter.chronologer.service.PetsService;
import com.critter.chronologer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.ValidationAnnotationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetsController {
    @Autowired
    PetsService petsService;
    @Autowired
    UsersService usersService;
    @PostMapping("/pets")
    public Pets savePet(@RequestBody Pets pet){
        return petsService.addPet(pet);
    }

}
