package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity{

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Integer remainingQuantity;
    // in product side will have less load when bring category
    // we are writing relationship in product entity
    @ManyToMany
    @JoinTable(name = "product_category_rel",
                joinColumns = @JoinColumn(name = "p_id"),
                inverseJoinColumns = @JoinColumn(name = "c_id"))
    private List<Category> categoryList;





}
