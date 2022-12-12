

package com.sdsu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsu.model.entity.Product;

import feign.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(@Param("id") Integer id);

    List<Product> findAllByOrderByIdDesc();
}