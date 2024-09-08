package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.client.CurrencyApiClient;
import com.cydeo.lab08rest.dto.OrderDTO;
import com.cydeo.lab08rest.dto.UpdateOrderDTO;
import com.cydeo.lab08rest.entity.Order;

import com.cydeo.lab08rest.enums.Currency;
import com.cydeo.lab08rest.exception.CurrencyTypeNotFoundException;
import com.cydeo.lab08rest.exception.NotFoundException;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.OrderRepository;
import com.cydeo.lab08rest.service.CartService;
import com.cydeo.lab08rest.service.CustomerService;
import com.cydeo.lab08rest.service.OrderService;
import com.cydeo.lab08rest.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class OrderServiceImpl implements OrderService {
    @Value("${access_key}")
    private String accessKey;
    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final CurrencyApiClient currencyApiClient;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService,
                            CartService cartService, PaymentService paymentService, CurrencyApiClient currencyApiClient) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.currencyApiClient = currencyApiClient;
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
        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(()->new NotFoundException("Order could not be found"));
        //we have to check of the Order fields (if they have another dependencies in the order like customer, cart) exists or not (creating private method if something exists in dB)
        validateRelatedFieldsAreExist(orderDTO);
        // if fields exists, then convert orderDTO to order and save it
        Order willBeUpdatedOrder = mapperUtil.convert(orderDTO, new Order());
        Order updatedOrder = orderRepository.save(willBeUpdatedOrder);
        // converted again the updated one and returned
        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    @Override// New update method with the fields that is updatable
    public OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new NotFoundException("Order could not be found"));
        // if we have another business logic for update method we are implementing here f
        // for exp: if we are getting the same value, it is not necessary to update the actual value
        // i will create one boolean variable and by default is false
        boolean changeDetected = false;
        if(!order.getPaidPrice().equals(updateOrderDTO.getPaidPrice())){
            order.setPaidPrice(updateOrderDTO.getPaidPrice());
            changeDetected=true;
        }
        if(!order.getTotalPrice().equals(updateOrderDTO.getTotalPrice())){
            order.setTotalPrice(updateOrderDTO.getTotalPrice());
            changeDetected=true;
        }
        //if there is any change update the order and return it
        if(changeDetected){
           Order updateOrder =  orderRepository.save(order);
            return mapperUtil.convert(updateOrder, new OrderDTO());
        }else{ // if is not any change
            throw new RuntimeException(" No changes detected");
        }

    }

    @Override
    public OrderDTO retrieveOrderDetailById(Long id, Optional<String> currency) { // we implement this structure
        // find the order based on id, convert and return it
       Order order = orderRepository.findById(id).orElseThrow(
               ()-> new NotFoundException("Order could not be found"));
       // if I have currency value from the user it will use this structure and this line will execute
        // if not find the order will not execute
       currency.ifPresent(curr -> { //before is saving in DB we are doing new prices
           validateCurrency(curr);
           //get the  currency data based on currency type
           //create a private method to separate steps(which is accepting the currency and returning thr currency result)
           //for example i am giving the euro and they returning the Euro Value

           BigDecimal currencyRate =getCurrencyRate(curr);  // CONSUME API
           //do calculations and set new paidPrice and totalPrice (this new prices)
           BigDecimal newPaidPrice = order.getPaidPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP);// setting new value(if they accept euro will return euro, if accept usd return usd)
           BigDecimal newTotalPrice = order.getTotalPrice().multiply(currencyRate).setScale(2,RoundingMode.HALF_UP);
           // set the value to order that we retrieved
           order.setPaidPrice(newPaidPrice);
           order.setTotalPrice(newTotalPrice);
       });
       //convert and return it
       return mapperUtil.convert(order,new OrderDTO());
    }

    private void validateCurrency(String curr) { // we are validating currency here before providing getCurrencyMethod()
        // check if the currency is valid currency
        //  type ENUM
        List<String> currencies = Stream.of(Currency.values())// converting ENUM TO THE LIST
                .map(currency -> currency.value)
                .collect(Collectors.toList());
        boolean isCurrencyValid = currencies.contains(curr);
        if (!isCurrencyValid)
            throw  new CurrencyTypeNotFoundException("Currency type for " + curr + "could not be found");
    }

    private BigDecimal getCurrencyRate(String currency) {// this method is returning Double (sending value and accept the request
   //consume api //request part//           this can be manage in different class
        // it is returning me map -> we save response inside quotes map
        Map<String, Double> quotes= (Map<String, Double>) currencyApiClient.getCurrencyRate(accessKey, currency, "USD", 1).get("quotes"); // get("quotes") were cast to object (Map<String, Object>)
        Boolean isSuccess = (Boolean) currencyApiClient.getCurrencyRate(accessKey, currency, "USD", 1).get("success");//success were cast to (Boolean)
        if(!isSuccess){// Before we do action we are checking if the feignClient working
            throw new RuntimeException("API IS DOWN");
        }
        String expectedCurrency = "USD" + currency.toUpperCase(); // String manipulation and get correct currency
        BigDecimal currencyRate= BigDecimal.valueOf(quotes.get(expectedCurrency)); // converting to big decimal
        return currencyRate;
        }



    // my private method (not for business logic of orderService)to validate existing fields dependencies from my orderDTO
    private void validateRelatedFieldsAreExist(OrderDTO orderDTO) {
        //in this method we have 3 different service and make sure that they have fields
        //we will create service and existById method and verify
        if(!customerService.existById(orderDTO.getCustomerId())) { // reversing business logic
            throw new NotFoundException("Customer could not be found");
        }
        if(!cartService.existById(orderDTO.getCartId())){
            throw new NotFoundException("Order could not be found");
        }
        if(!paymentService.existById(orderDTO.getPaymentId())){
            throw new NotFoundException("Payment could not be found");
        }

    }
}
