package com.sdsu.repository;

import com.sdsu.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findById(@Param("id") Integer id);

    List<Order> findAllByOrderByIdDesc();
}
