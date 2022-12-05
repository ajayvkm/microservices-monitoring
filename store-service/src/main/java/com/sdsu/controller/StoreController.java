package com.sdsu.controller;

import com.sdsu.dto.OrderDTO;
import com.sdsu.model.Account;
import com.sdsu.model.Order;
import com.sdsu.model.Product;
import com.sdsu.repository.AccountServiceClient;
import com.sdsu.repository.OrderServiceClient;
import com.sdsu.repository.ProductServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8762")
public class StoreController {

    @Value("${msg:Config Server is not working. Please check...}")
    public String msg;

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok(this.msg);
    }

    private final ProductServiceClient productService;
    private final OrderServiceClient orderService;
    private final AccountServiceClient accountService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders()
    {

        log.warn("Fetching all orders...");
        List<Order> orders = orderService.findAll();
        Map<Integer, Account> accounts = new HashMap<>();
        Map<Integer, Product> products = new HashMap<>();

        log.warn("Fetching accounts of orders...");
        orders.stream()
                .filter(o->!accounts.containsKey(o.getAccountId()))
                .map(o->accountService.findById(o.getAccountId()))
                .forEach(a->accounts.put(a.getId(), a));

        log.warn("Fetching products of orders...");
        orders.stream()
                .filter(o->!products.containsKey(o.getProductId()))
                .map(o->productService.findById(o.getProductId()))
                .forEach(a->products.put(a.getId(), a));

        log.warn("Generating composite of orders...");
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orders.forEach(o->{
            orderDTOList.add(new OrderDTO(
                    o.getId(),
                    o.getQuantity(),
                    o.getTotalPrice(),
                    o.getDiscountedPrice(),
                    accounts.get(o.getAccountId()).getFullName(),
                    products.get(o.getProductId()).getName()
            ));
        });
        return ResponseEntity.ok(orderDTOList);
    }

    @PutMapping("/place-orders")
    @Operation(summary = "Use this api to update any order.")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
