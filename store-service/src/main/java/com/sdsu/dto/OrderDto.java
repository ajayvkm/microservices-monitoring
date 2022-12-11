package com.sdsu.dto;

import com.sdsu.model.Account;
import com.sdsu.model.Order;
import com.sdsu.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Order order;
    private Account account;
    private Product product;
}
