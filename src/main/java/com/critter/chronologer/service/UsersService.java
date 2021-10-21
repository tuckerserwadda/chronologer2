package com.critter.chronologer.service;

import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.Users;
import com.critter.chronologer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Users  addUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
    public Users getUsers( String userName){
        return usersRepository.findByUserName(userName);
    }
}
