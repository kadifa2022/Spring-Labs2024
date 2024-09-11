package com.cydeo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "paymentDetails")
@Getter
@Setter
@NoArgsConstructor
public class PaymentDetail {
    public PaymentDetail(BigDecimal commissionAmount, BigDecimal merchantPayoutAmount, LocalDate payoutAmount) {
        this.commissionAmount = commissionAmount;
        this.merchantPayoutAmount = merchantPayoutAmount;
        this.payoutAmount = payoutAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal commissionAmount;
    private BigDecimal merchantPayoutAmount;

    @Column(columnDefinition = "DATE")
    private LocalDate payoutAmount;
    @OneToOne(mappedBy = "paymentDetail")
    private Payment payment;


}
