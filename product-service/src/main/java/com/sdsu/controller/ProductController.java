package com.sdsu.controller;

import com.sdsu.model.Product;
import com.sdsu.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@Api("Product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/")
    @Operation(summary = "Welcome")
    @ApiOperation(value = "welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to product service !");
    }

    @Autowired
    private final ProductRepository repository;

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns unsecured test data from backend")
    public ResponseEntity<Product> get(@PathVariable("id") Integer id)
    {
        Product product = repository.findById(id).orElse(null);
        log.info("Product {} detail fetched {}", id, product);
        return ResponseEntity.ok(product);
    }

    @GetMapping()
    @ApiOperation(value = "Get All The Products")
    public ResponseEntity<List<Product>> getAll()
    {
        java.util.List<Product> allProducts = repository.findAll();
        log.info("Executing fetching all products {}", allProducts);
        return ResponseEntity.ok(allProducts);
    }
}
