package com.critter.chronologer.config;

import com.critter.chronologer.entity.Users;
import com.critter.chronologer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * this is a custom user detail service class and implements thee userDetails Service
 * it has one override method loadUserByUserName
 * the user is gotten from the users Repository
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserName(userName);
        // check if user is null then throw an exception
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        // return the user form the Custom User Details

        return new CustomUserDetails(user);
    }
}
