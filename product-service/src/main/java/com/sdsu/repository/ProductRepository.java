package com.sdsu.repository;

import com.sdsu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class ProductRepository implements JpaRepository<Product, Integer> {
    /*private final static Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "5 saatlik şarj aleti", 100D));
        products.put(2, new Product(2, "traş makinası", 5D));
        products.put(3, new Product(3, "klavye", 200D));
        products.put(4, new Product(4, "sırt çantası", 50D));
    }*/

    Optional<Product> findById(Integer id);

    Optional<Collection<Product>> findAll();
}
