package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;


import java.util.List;
import java.util.Optional;

public interface OrderService {


   List<OrderDTO> retrieveOrderList();
   OrderDTO updateOrder(OrderDTO orderDTO);

   OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO);


   OrderDTO retrieveOrderDetailById(Long id, Optional<String> currency);
}
