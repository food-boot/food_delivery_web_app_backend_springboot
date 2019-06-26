package com.food.delivery.app.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.food.delivery.app.ws.io.entity.OrderEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

	OrderEntity findByOrderId(String orderId);
}
