package com.sdsu.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private final Integer id;
    private final Integer quantity;
    private final Double totalPrice;
    private final Double discountedPrice;
    private final String accountName;
    private final String productName;
}
