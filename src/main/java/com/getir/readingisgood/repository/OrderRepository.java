package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findAllByOrderByOrderLongUxAsc();

    List<Order> findByCustomerId(Long customerId);
}
