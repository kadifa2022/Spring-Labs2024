package com.cydeo.client;

import com.cydeo.dto.weather.WeatherDTO;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com", name = "WEATHER-CLIENT")
public interface WeatherApiClient {

    @GetMapping("/current") //object in return for now/ dto
    WeatherDTO getCurrentWeather(@RequestParam(value = "access_key") String key,
                                 @RequestParam(value = "query") String city);


}
