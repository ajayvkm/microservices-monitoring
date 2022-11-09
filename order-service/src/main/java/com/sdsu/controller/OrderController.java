package com.sdsu.controller;

import com.sdsu.model.Order;
import com.sdsu.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to order service !");
    }

    private final OrderRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable("id") Integer id) {
        final Order order = repository.findById(id).get();
        log.info("Order {} detail fetched {}", id, order);
        return ResponseEntity.ok(order);
    }

    @GetMapping()
    public ResponseEntity<Collection<Order>> getAll() {
        final Collection<Order> orders = repository.findAll().get();
        log.info("Executing fetching all orders {}", orders);
        return ResponseEntity.ok(orders);
    }
}
