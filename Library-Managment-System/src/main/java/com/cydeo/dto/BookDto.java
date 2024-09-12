package com.cydeo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @JsonIgnore
    private String id;

    private String title;
    private String author;

    private String isbn;
    private boolean available;
}
