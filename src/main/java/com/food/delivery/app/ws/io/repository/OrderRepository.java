package com.food.delivery.app.ws.io.repository;

import org.springframework.data.repository.CrudRepository;

import com.food.delivery.app.ws.io.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
