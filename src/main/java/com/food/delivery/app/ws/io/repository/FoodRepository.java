package com.food.delivery.app.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.delivery.app.ws.io.entity.FoodEntity;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {

	FoodEntity findByFoodId(String foodId);
}
