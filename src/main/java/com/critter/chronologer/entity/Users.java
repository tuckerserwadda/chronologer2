package com.critter.chronologer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
// for all getters and setters
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    // the username should be unique and not null
    @Column(unique = true, nullable = false)
    private String userName;

    private  String password;
    private String firstName;
    private String lastName;
    private String role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // the email address should be unique and non-nullable
    @Column(unique = true, nullable = false)
    private String emailAddress;
    // gett the address by using the embeddable annotation
    @Embedded
    private ResidentialAddress residentialAddress;
}
