package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ResponseWrapper for customizing body(for customizing dto, want to pass status code inside JSON, message
    // ResponseEntity for custom Header and  custom status code

    @GetMapping
    public ResponseEntity<ResponseWrapper> getOrderList(){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved.",
                orderService.retrieveOrderList(), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@Valid@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order are successfully updated.",
                orderService.updateOrder(orderDTO), HttpStatus.OK));

    }
    @PutMapping("/{id}") // this is new update method with new dto that we created with the only  field that is changeable
    public ResponseEntity<ResponseWrapper> updateOrderById(@PathVariable("id") Long id, @Valid @RequestBody UpdateOrderDTO updateOrderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order is successfully updated",
                orderService.updateOrderById(id, updateOrderDTO),HttpStatus.OK));
    }
    @GetMapping("/{id}")                        // will accept id as parameter     //we are accepting Currency as optional String without worrying to have nullPointerException
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("id") Long id, @RequestParam(required = false)Optional<String> currency){
        return ResponseEntity.ok(new ResponseWrapper("Order is successfully retrieved",
                orderService.retrieveOrderDetailById(id,currency),HttpStatus.OK));
    }





}
