package com.cydeo.client;

import com.cydeo.dto.DogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "https://api.thedogapi.com/v1", name="DOG-SERVICE")
public interface DogClient {


   // https://api.thedogapi.com/v1/breeds

    @GetMapping("/breeds")
    List<DogDTO> getAllDogBreeds();


    @GetMapping("breeds/{id}")
    DogDTO getById(@PathVariable("id") Long id);






}
