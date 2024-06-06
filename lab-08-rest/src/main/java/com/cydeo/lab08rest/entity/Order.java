package com.cydeo.lab08rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity{
    @OneToOne(cascade = CascadeType.MERGE)
    private Cart cart;
    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}
