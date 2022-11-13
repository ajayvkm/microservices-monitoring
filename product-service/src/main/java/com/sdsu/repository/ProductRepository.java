package com.sdsu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsu.model.Product;

import feign.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    /*private final static Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "5 saatlik şarj aleti", 100D));
        products.put(2, new Product(2, "traş makinası", 5D));
        products.put(3, new Product(3, "klavye", 200D));
        products.put(4, new Product(4, "sırt çantası", 50D));
    }*/

    Optional<Product> findById(@Param("id") Integer id);

    List<Product> findAll();
}
