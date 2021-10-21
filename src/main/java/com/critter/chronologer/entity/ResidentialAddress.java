package com.critter.chronologer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/***
 * we need to know the address of the user but we dont want to keep
 * the address as a seperate table in the data base
 * so we create an embeddable class Residential address
 */
@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@AttributeOverride(
        name="residentialAddress",
        column = @Column(name = "residentialAddress")
)
public class ResidentialAddress {
    private String streetAddress;
    private Integer buildingNumber;
    private String aptNumber;
    private String city;
    private String zipCode;
    private String state;

}
