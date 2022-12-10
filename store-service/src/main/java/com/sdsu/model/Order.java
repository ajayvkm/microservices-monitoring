package com.sdsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public Integer id;
    public Integer productId;
    public Integer accountId;
    public Integer quantity;
    public Double price;
    public Double discountedPrice;
    public String status;
    public String createdDate;
    public String createdBy;
    public Payment payment;
    public String paymentStatusMessage;
}
