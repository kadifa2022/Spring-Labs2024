package com.cydeo.controller;

import com.cydeo.client.DogClient;
import com.cydeo.dto.ResponseWrapper;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogClient dogClient;

    public DogController(DogClient dogClient) {
        this.dogClient = dogClient;
    }

    @GetMapping("/breeds")
    public ResponseEntity<ResponseWrapper> getAllDogsBreeds(){
        return  ResponseEntity.ok(new ResponseWrapper("Dogs are successfully retrieved", dogClient.getAllDogBreeds()));
    }

    @GetMapping("breeds/{id}")
    public ResponseEntity<ResponseWrapper> getDogById(@PathVariable ("id") Long id){
        return  ResponseEntity.ok(new ResponseWrapper("Dog is successfully retrieved",dogClient.getById(id)));
    }

}
