package com.cydeo.lab08rest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionWrapper { //2. adding fields for validationError in constructor
    //NotFoundException fields
    public String message;
    public HttpStatus httpStatus;
    private LocalDateTime timestamp;

    //2. validation error
    private Integer errorCount;
    private List<ValidationError> validationErrorList;

    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
