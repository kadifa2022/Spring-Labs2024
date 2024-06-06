package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> retrieveAddressList();

    Object updateAddress(AddressDTO addressDTO);

    Object createAddress(AddressDTO addressDTO);

    Object findAddressListByStartsWith(String address);

    Object findAddressListByCustomer(Long id);

    Object findAddressListByCustomerAndName(Long customerId, String name);
}
