package com.critter.chronologer.repository;

import com.critter.chronologer.entity.Gender;
import com.critter.chronologer.entity.ResidentialAddress;
import com.critter.chronologer.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
// for production test use @DataJpaTest because the database will not be affected
class UsersServiceRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;
// save user
    @Test
    public void saveUser(){
        ResidentialAddress residentialAddress = ResidentialAddress.builder()
                .streetAddress("82 brickline road")
                .buildingNumber(5)
                .aptNumber("A35")
                .city("Chelmsford")
                .state("M.A")
                .zipCode("01851-01852")
                .build();
        Users user = Users.builder()
                .userName("Tuckerdanz")
                .emailAddress("serwadda@gmail.com")
                .firstName("Tucker")
                .lastName("Serwadda")
                .password("Tuc2017@04")
                .gender(Gender.male)
                .residentialAddress(residentialAddress)
                .build();
        usersRepository.save(user);
    }
    // get all users
    @Test
    public void getAllUsers(){
        List<Users> ListOfUsers = (List<Users>) usersRepository.findAll();
        System.out.println("list of users" + ListOfUsers);
    }
    @Test
    public void getUserByUserName(){
        Users user = usersRepository.findByUserName("Tuckerdanz");
        System.out.println("the user is: " + user);
    }
    @Test
    public void getUserAddress(){
        ResidentialAddress address = usersRepository.getUserResidentialAddress("Tuckerdanz");
        System.out.println("the user Address is: " + address);

    }


}