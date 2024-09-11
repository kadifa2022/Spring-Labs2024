package com.cydeo.bootstrep;

import com.cydeo.entity.*;
import com.cydeo.enums.PaymentStatus;
import com.cydeo.repository.CartRepository;
import com.cydeo.repository.ItemRepository;
import com.cydeo.repository.MerchantRepository;
import com.cydeo.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {

    private final PaymentRepository paymentRepository;
    private final MerchantRepository merchantRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public DataGenerator(PaymentRepository paymentRepository, MerchantRepository merchantRepository, CartRepository cartRepository, ItemRepository itemRepository) {
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Payment payment1 = new Payment(LocalDate.of(2022,4,19),new BigDecimal("150000"), PaymentStatus.SUCCESS);
        PaymentDetail paymentDetail1 = new PaymentDetail(new BigDecimal("140000"),new BigDecimal("10000"),LocalDate.of(2022,4,24));

        payment1.setPaymentDetail(paymentDetail1);

        Payment payment2 = new Payment(LocalDate.of(2022,4,25),new BigDecimal("100000"), PaymentStatus.FAILURE);
        PaymentDetail paymentDetail2 = new PaymentDetail(new BigDecimal("90000"),new BigDecimal("5000"),LocalDate.of(2022,4,29));

        payment2.setPaymentDetail(paymentDetail2);

        Merchant merchant1 = new Merchant("Amazon","M123", new BigDecimal("0.25"), new BigDecimal("3.45"),5 );
        payment1.setMerchant(merchant1);
        payment2.setMerchant(merchant1);
        Item item1 = new Item("Milk","M01");
        Item item2 = new Item("Suger","S01");
        Item item3 = new Item("Bread","B01");

        Cart cart1 = new Cart();
        Cart cart2= new Cart();

        cart1.setItemList(Arrays.asList(item1,item2,item3));
        cart2.setItemList(Arrays.asList(item1,item2));

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);


        cartRepository.save(cart1);
        cartRepository.save(cart2);


        //save merchant to merchant table
        merchantRepository.save(merchant1);



        //sae payments to payments table
        paymentRepository.save(payment1);
        paymentRepository.save(payment2); // will be saved payment2 and paymentDetail2 at the same time because of cascading

        System.out.println(paymentRepository.findById(2L).get().getAmount());
        System.out.println(paymentRepository.findById(2L).get().getPaymentDetail().getCommissionAmount());

        //paymentRepository.delete(payment1);


    }
}
