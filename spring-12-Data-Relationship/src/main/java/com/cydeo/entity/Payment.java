package com.cydeo.entity;

import com.cydeo.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payments")
public class Payment {

    public Payment(LocalDate createdDate, BigDecimal amount, PaymentStatus paymentStatus) {
        this.createdDate = createdDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    private LocalDate createdDate;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Merchant merchant;

    @OneToOne(cascade = CascadeType.ALL)// What ever action we are using for payment table  we are using
    // for payment_details table  also without payment details repository
   //  @JoinColumn(name = "myForeignKey") if we need to re-name Foreign key column
//    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private PaymentDetail paymentDetail;



}
