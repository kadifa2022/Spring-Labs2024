package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartItem extends BaseEntity{

    private Integer quantity;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
}
