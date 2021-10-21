package com.critter.chronologer.service;

import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.Users;
import com.critter.chronologer.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {
    @Autowired
    PetsRepository petsRepository;
    public Pets addPet(Pets pet){
        return petsRepository.save(pet);
    }

}
