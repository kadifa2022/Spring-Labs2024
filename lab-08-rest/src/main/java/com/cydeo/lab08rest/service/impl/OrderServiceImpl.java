package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.entity.Order;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.CustomerService;
import com.cydeo.lab08rest.service.OrderService;
import com.cydeo.lab08rest.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final CartService cartService;
    private final PaymentService paymentService;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService, CartService cartService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }


    @Override
    public List<OrderDTO> retrieveOrderList() {
        return orderRepository.findAll().stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        //1st step look for the orderId inside the DB and throw exception
        Order Order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(()->new RuntimeException("Order could not be found"));
        //we have to check of the Order fields exists or not (creating private method if something exists in dB)
        validateRelatedFieldsAreExist(orderDTO);
        // if fields exists, then convert orderDTO to order and save it
        Order willBeUpdatedOrder = mapperUtil.convert(orderDTO, new Order());
        Order updatedOrder = orderRepository.save(willBeUpdatedOrder);
        // converted again the updated one and returned
        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    // my private method (not for business logic of orderService)to validate existing fields on my orderDTO
    private void validateRelatedFieldsAreExist(OrderDTO orderDTO) {
        //in this method we have 3 different service and make sure that they have fields
        //we will create service and existById method and verify
        if(!customerService.existById(orderDTO.getCustomerId())) { // reversing business logic
            throw new RuntimeException("Customer not be found");
        }
        if(!cartService.existById(orderDTO.getCartId())){
            throw new RuntimeException("Order not be found");
        }
        if(!paymentService.existById(orderDTO.getPaymentId())){
            throw new RuntimeException("Payment not be found");
        }

    }
}
