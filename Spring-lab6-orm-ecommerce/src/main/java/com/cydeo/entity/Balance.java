package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Balance extends BaseEntity {


    private BigDecimal amount;
   //we are creating connections in balance table because we don't want to bring balance when we call customer in customer table
    @OneToOne // holding foreign key as costumer_id
    private Customer customer;

}
