package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Address extends BaseEntity{


    private String name;
    private String street;
    private String zipCode;
    @ManyToOne
    private Customer customerId;


}
