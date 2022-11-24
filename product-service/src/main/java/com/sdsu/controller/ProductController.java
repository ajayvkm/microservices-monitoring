package com.sdsu.controller;

import com.sdsu.model.dto.ProductDto;
import com.sdsu.model.entity.Product;
import com.sdsu.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/")
    @Operation(summary = "Welcome API.")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to product service !");
    }

    @Autowired
    private final ProductRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Find product by product id.")
    public ResponseEntity<Product> get(@PathVariable("id") Integer id)
    {
        try {
            Optional<Product> productData = repository.findById(id);
            if (productData.isPresent()) {
                log.info("ProductDto {} detail fetched {}", id, productData.get());
                return new ResponseEntity<>(productData.get(), HttpStatus.OK);
            } else {
                log.warn("ProductDto {} details not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Search product by id failed " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get All The Products")
    public ResponseEntity<List<Product>> getAll()
    {
        try {
            List<Product> allProducts = repository.findAll();
            if (allProducts.isEmpty()) {
                log.warn("Products list empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Executing fetching all products {}", allProducts);
            return new ResponseEntity<>(allProducts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Executing fetching all products failed " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Use this api to add new product.")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product)
    {
        try {
            Product _product = repository.save(new Product(product.getName(), product.getPrice(), new Date(), "system"));
            log.info("New product added to the database {}", _product.getId());
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while saving the product");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Use this api to update any product.")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto product) {
        Optional<Product> productData = repository.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setName(product.getName());
            _product.setPrice(product.getPrice());
            _product.setCreatedBy(product.getCreatedBy());
            Product savedProduct = repository.save(_product);
            log.info("Product updated successfully to the database {}", _product.getId());
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            log.error("Error occurred while updating the product {} {}", id, product.getName());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Use this api to delete any single product.")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            log.info("Product deleted successfully from the database {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.info("Order failed to delete from the database {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    @Operation(summary = "Use this api to delete all product.")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
