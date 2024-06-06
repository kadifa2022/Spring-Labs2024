package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.AddressRepository;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<AddressDTO> retrieveAddressList() {
        return null;
    }

    @Override
    public Object updateAddress(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public Object createAddress(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public Object findAddressListByStartsWith(String address) {
        return null;
    }

    @Override
    public Object findAddressListByCustomer(Long id) {
        return null;
    }

    @Override
    public Object findAddressListByCustomerAndName(Long customerId, String name) {
        return null;
    }
}
