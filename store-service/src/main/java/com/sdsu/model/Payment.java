package com.sdsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int id;
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private double amount;
}