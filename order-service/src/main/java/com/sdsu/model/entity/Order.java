package com.sdsu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Double price;

    @Column(name = "discountedprice")
    private Double discountedPrice;

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "createdby")
    private String createdBy;

    public Order(Integer productId, Integer accountId, Integer quantity, Double price, Double discountedPrice,
            Date createdDate, String createdBy) {
        this.productId = productId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
}