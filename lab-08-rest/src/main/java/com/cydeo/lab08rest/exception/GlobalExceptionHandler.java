package com.cydeo.lab08rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice // this class will capture all exceptions
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> processNotFoundException(NotFoundException ex){
        //create JSON body and return it
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);
        // this is actual status code              // what we are sending back
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)// this method will be triggered when @Valid happened
    public ResponseEntity<ExceptionWrapper> processValidationError(MethodArgumentNotValidException ex){//ex is method argument
        return handleMethodArgumentNotValid(ex);


    }

    protected ResponseEntity<ExceptionWrapper> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid Input(s)", HttpStatus.BAD_REQUEST);
        List<ValidationError> validationErrorList = new ArrayList<>();

        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            String fieldName= ((FieldError)error).getField();
            Object rejectValue=((FieldError)error).getRejectedValue();
            String errorMessage=error.getDefaultMessage();

        }
        return null;
    }


}
