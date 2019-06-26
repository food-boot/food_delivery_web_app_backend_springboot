package com.food.delivery.app.ws.service;

import java.util.List;

import com.food.delivery.app.ws.shared.dto.FoodDto;

public interface FoodService {

	FoodDto createFood(FoodDto food);
	FoodDto getFoodById(String foodId);
	FoodDto updateFoodDetails(String foodId, FoodDto foodDetails);
	void deleteFoodItem(String id);
	List<FoodDto> getFoods();
}
