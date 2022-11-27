package com.sdsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {

    private Integer id;
    private Integer productId;
    private Integer accountId;
    private Integer quantity;

    private Double totalPrice;
    private Double discountedPrice;

    public Order(Integer id, Integer productId, Integer accountId, Integer quantity, Double totalPrice, Double discountedPrice) {
        this.id = id;
        this.productId = productId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
    }
}
