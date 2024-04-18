package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{

    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    @OneToOne
    private Cart cart;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Payment payment;

}
