package com.sdsu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsu.model.entity.Product;

import feign.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    /*private final static Map<Integer, ProductDto> products = new HashMap<>();

    static {
        products.put(1, new ProductDto(1, "5 saatlik şarj aleti", 100D));
        products.put(2, new ProductDto(2, "traş makinası", 5D));
        products.put(3, new ProductDto(3, "klavye", 200D));
        products.put(4, new ProductDto(4, "sırt çantası", 50D));
    }*/

    Optional<Product> findById(@Param("id") Integer id);

    List<Product> findAll();
}
