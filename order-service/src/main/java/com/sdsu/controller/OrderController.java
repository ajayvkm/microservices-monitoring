package com.sdsu.controller;

import com.sdsu.model.dto.OrderDto;
import com.sdsu.model.entity.Order;
import com.sdsu.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;

    @GetMapping("/")
    @Operation(summary = "Welcome API.")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to order service !");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find order by order id.")
    public ResponseEntity<Order> get(@PathVariable("id") Integer id)
    {
        try {
            Optional<Order> orderData = repository.findById(id);
            if (orderData.isPresent()) {
                log.info("Order {} detail fetched {}", id, orderData.get());
                return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
            } else {
                log.warn("Order {} details not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Search order by id failed " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all the orders")
    public ResponseEntity<List<Order>> getAll()
    {
        try {
            List<Order> allOrders = repository.findAll();
            if (allOrders.isEmpty()) {
                log.warn("Orders list empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Executing fetching all orders {}", allOrders);
            return new ResponseEntity<>(allOrders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Executing fetching all orders failed " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Use this api to add new order.")
    public ResponseEntity<Order> addNewOrder(@RequestBody OrderDto order)
    {
        try {
            Order _order = repository.save(new Order(order.getProductId(), order.getAccountId(), order.getQuantity(), order.getTotalPrice(), order.getDiscountedPrice(), new Date(), "system"));
            log.info("New order added to the database {}", _order.getId());
            return new ResponseEntity<>(_order, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while saving the order");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Use this api to update any order.")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDto order) {
        Optional<Order> orderData = repository.findById(id);

        if (orderData.isPresent()) {
            Order _order = orderData.get();
            _order.setProductId(order.getProductId());
            _order.setAccountId(order.getAccountId());
            _order.setPrice(order.getTotalPrice());
            _order.setDiscountedPrice(order.getDiscountedPrice());
            _order.setCreatedDate(new Date());
            _order.setCreatedBy("system");
            _order.setQuantity(order.getQuantity());
            Order savedOrder = repository.save(_order);
            log.info("Order updated successfully to the database {}", _order.getId());
            return new ResponseEntity<>(savedOrder, HttpStatus.OK);
        } else {
            log.error("Error occurred while updating the order {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Use this api to delete any single order.")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            log.info("Order deleted successfully from the database {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.info("Order failed to delete from the database {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    @Operation(summary = "Use this api to delete all order.")
    public ResponseEntity<HttpStatus> deleteAllOrders() {
        try {
            repository.deleteAll();
            log.info("All orders deleted successfully from the database.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.info("All orders failed to delete from the database.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
