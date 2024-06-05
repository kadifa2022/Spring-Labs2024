package com.cydeo.service.impl;

import com.cydeo.client.CountryApiClient;
import com.cydeo.client.WeatherApiClient;
import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.weather.WeatherDTO;
import com.cydeo.entity.Address;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import com.cydeo.utill.MapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {



    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final WeatherApiClient weatherApiClient;
    private final CountryApiClient countryApiClient;

    @Value("${access_key}")
    private String access_key;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherApiClient weatherApiClient, CountryApiClient countryApiClient) {

        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherApiClient = weatherApiClient;
        this.countryApiClient = countryApiClient;
    }


    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapperUtil.convert(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) throws Exception{
        Address foundAddress = addressRepository.findById(id)
                .orElseThrow(()-> new Exception("No Address Found!"));
        AddressDTO addressDTO = mapperUtil.convert(foundAddress, new AddressDTO());
        //we will get current temperature and set based on city, and return DTO
       // addressDTO.setCurrentTemperature(retrieveCurrentWeather(addressDTO.getCity()).getCurrent().getTemperature());
        addressDTO.setCurrentTemperature(retrieveTemperatureByCity(addressDTO.getCity()));
        addressDTO.setFlag(retrieveFlagByCountry(addressDTO.getCountry()));

        return addressDTO;
    }

    private String retrieveFlagByCountry(String country) {// if we're returning list than structures needs to change with(get(0))
        return countryApiClient.getCountryInfo(country).get(0).getFlags().getPng();
    }

    private Integer retrieveTemperatureByCity(String city) {
        return weatherApiClient.getCurrentWeather(access_key, city).getCurrent().getTemperature();
    }

    private WeatherDTO retrieveCurrentWeather(String city) {
        return weatherApiClient.getCurrentWeather(access_key, city);


    }


    @Override
    public AddressDTO update(AddressDTO addressDTO) throws Exception {
        addressRepository.findById(addressDTO.getId())
                .orElseThrow(()->new Exception("No Address Found"));
        Address addressToSave = mapperUtil.convert(addressDTO, new Address());

        addressRepository.save(addressToSave);
        AddressDTO updateAddress = mapperUtil.convert(addressToSave, new AddressDTO());

        return updateAddress;
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) throws Exception {
        Optional<Address> foundAddress = addressRepository.findById(addressDTO.getId());
        if(foundAddress.isPresent()){
            throw new Exception("Address Already Exists!");
        }
        Address addressToSave =mapperUtil.convert(addressDTO, new Address());
        addressRepository.save(addressToSave);

        return mapperUtil.convert(addressToSave, new AddressDTO());
    }

}
