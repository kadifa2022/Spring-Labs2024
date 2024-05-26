package com.cydeo.entity;

import com.cydeo.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address extends BaseEntity{
    private String street;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private Student student;
    private Parent parent;
    private Teacher teacher;
}
