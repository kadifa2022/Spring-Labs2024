package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override // with this method we don't need to bring whole object
    public boolean existById(Long id) {// coming from jpa repository
        return customerRepository.existsById(id);
    }
}
