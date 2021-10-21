package com.critter.chronologer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pets {
    /**
     * you can decide on how the id is generated with a particular sequence
     * we use the Sequence generator annotation
     */
    @Id
    @SequenceGenerator(
            name = "pets_sequence",
            sequenceName = "pets_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pets_sequence"
    )
    private Long petId;
    private String petName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private PetType petType;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String UserName;
}
