package com.critter.chronologer.repository;

import com.critter.chronologer.entity.Pets;
import com.critter.chronologer.entity.ResidentialAddress;
import com.critter.chronologer.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    // add custom methods
    // find a user by userName
     Users findByUserName(String userName);
    // find user's address using username
    // with this we can't find by then we use JPQl query
    @Query("Select u.residentialAddress from Users u where u.userName = userName")
    ResidentialAddress getUserResidentialAddress(String userName);

}
