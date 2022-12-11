package com.sdsu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    public Integer productId;
    public Integer accountId;
    public Integer quantity;
    public Double totalPrice;
    public Double discountedPrice;
}
