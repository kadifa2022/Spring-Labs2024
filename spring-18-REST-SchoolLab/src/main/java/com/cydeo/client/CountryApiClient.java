package com.cydeo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@FeignClient(url = "https://restcountries.com/v3.1", name = "COUNTRY-CLIENT")
public interface CountryApiClient {
    ///https://restcountries.com/v3.1/name/Finland

    @GetMapping("/name/{countryName}")
    Object getCountryInfo(@PathVariable("countryName") String countryName);

}

