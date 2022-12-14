package com.sdsu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sdsu.dto.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "productid")
    private Integer productId;

    @Column(name = "accountid")
    private Integer accountId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalprice")
    private Double totalPrice;

    @Column(name = "discountedprice")
    private Double discountedPrice;


    @Column(name = "status")
    private String status;

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "createdby")
    private String createdBy;

    @Transient
    private Payment payment;

    @Transient
    private String paymentStatusMessage;

    public Order(Integer productId, Integer accountId, Integer quantity, Double totalPrice, Double discountedPrice, String status,
            Date createdDate, String createdBy, Payment payment, String paymentStatusMessage) {
        this.productId = productId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.payment = payment;
        this.paymentStatusMessage = paymentStatusMessage;
    }
}