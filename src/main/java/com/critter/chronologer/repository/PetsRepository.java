package com.critter.chronologer.repository;

import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.ResidentialAddress;
import com.critter.chronologer.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsRepository extends CrudRepository<Pets, Long> {
}
