package com.cydeo.client;

import com.cydeo.dto.country.CountryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(url = "https://restcountries.com/v3.1", name = "COUNTRY-CLIENT")
public interface CountryApiClient {
    ///https://restcountries.com/v3.1/name/Finland

    @GetMapping("/name/{countryName}") // if we have error Json cannot deserialize (from Array value)
    // return type must be List<CountryDTO> // if we're returning list than structures needs to change in Service impl.to (get(0)) get the first element from the list
    List<CountryDTO> getCountryInfo(@PathVariable("countryName") String countryName);

}

