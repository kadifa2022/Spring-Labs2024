package com.cydeo.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity{



    private String firstName;
    private String lastName;
    private String userName;
    private String email;


}
