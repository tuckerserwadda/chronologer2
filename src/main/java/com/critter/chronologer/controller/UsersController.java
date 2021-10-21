package com.critter.chronologer.controller;

import com.critter.chronologer.config.*;
import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.Users;
import com.critter.chronologer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UsersController {
    private String token;
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsersService usersService;

    @PostMapping("/login")

    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
            jwtRequest.getPassword()
                )
        );
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("invalid inputs ", e);
        }
         UserDetails myDetails= userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        token =jwtUtils.generateToken(myDetails);
        return new JwtResponse(token);
    }

    @PostMapping("/users")
    Users  saveUser( @RequestBody Users user){
        return usersService.addUser(user);
    }
    @GetMapping("/users/{userName}")
    public Users getUser( @PathVariable String userName){
        return usersService.getUsers(userName);
    }

}
