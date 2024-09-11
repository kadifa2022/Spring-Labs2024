package com.cydeo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "cart_item_rel",
            joinColumns =@JoinColumn(name = "c_id"),
            inverseJoinColumns = @JoinColumn(name = "i_id"))
    private List<Item> itemList;

}
