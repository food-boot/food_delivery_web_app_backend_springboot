package com.food.delivery.app.ws.io.repository;

import org.springframework.data.repository.CrudRepository;

import com.food.delivery.app.ws.io.entity.FoodEntity;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {

	FoodEntity findByFoodId(String foodId);
}
