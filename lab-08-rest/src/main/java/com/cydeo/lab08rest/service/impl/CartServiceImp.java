package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.repository.CartRepository;
import com.cydeo.lab08rest.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class
CartServiceImp  implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImp(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean existById(Long id) {
        return cartRepository.existsById(id);
    }
}
