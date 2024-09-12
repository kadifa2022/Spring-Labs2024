package com.cydeo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "books")
@NoArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String author;

    private String isbn;
    private boolean available;
}
