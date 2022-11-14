package com.sdsu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer productId;
    private Integer accountId;
    private Integer quantity;
    private Double totalPrice;
    private Double discountedPrice;
}
