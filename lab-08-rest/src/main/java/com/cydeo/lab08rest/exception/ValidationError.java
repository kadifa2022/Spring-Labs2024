package com.cydeo.lab08rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationError {//1. first

    private String errorField;
    private Object rejectedValue;
    private String reason;


}
