package com.sdsu.controller;

import com.sdsu.dto.OrderDto;
import com.sdsu.dto.OrderRequest;
import com.sdsu.dto.TransactionResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<List<OrderDto>> getOrders()
    {

        List<OrderDto> orderDtoList = new ArrayList<>();
        log.warn("Fetching all orders...");
        List<Order> orders = orderService.findAll();
        Map<Integer, Account> accounts = new HashMap<>();
        Map<Integer, Product> products = new HashMap<>();

        log.warn("Fetching accounts of orders...");
        for (Order order: orders) {
            OrderDto orderDTO = new OrderDto();
            orderDTO.setOrder(order);
            // Find the Account Details
            Integer accountId = order.getAccountId();
            if(null != accountId) {
                Account account = accountService.findById(accountId);
                orderDTO.setAccount(account);
            }

            Integer productId = order.getProductId();
            if (null != productId) {
                Product product = productService.findById(productId);
                if(null != product)
                    orderDTO.setProduct(product);
            }
            // Find Payment details
            orderDtoList.add(orderDTO);
        }
        
        return ResponseEntity.ok(orderDtoList);
    }

    @PostMapping("/place-orders")
    @Operation(summary = "Use this api to update any order.")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequest orderRequest) {
        ResponseEntity<TransactionResponse> orderResponseEntity = orderService.placeOrder(orderRequest);
        if(null != orderResponseEntity && orderResponseEntity.getStatusCode().is2xxSuccessful()) {
            return orderResponseEntity;
        } else {
            log.error("Error occurred while placing the order");
            return new ResponseEntity<>("Error occurred while placing the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
