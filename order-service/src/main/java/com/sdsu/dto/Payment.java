package com.sdsu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Integer id;
    private String paymentStatus;
    private String transactionId;
    private Integer orderId;
    private Double amount;
}