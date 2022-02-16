package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);
}
